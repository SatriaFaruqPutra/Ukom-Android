package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cucimobil.Model.Pembayaran
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponsePembayaran
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Locale

class PembayaranActivity : AppCompatActivity() {

    private lateinit var txtTotalHarga: TextView
    private lateinit var inputBayar: EditText
    private lateinit var inputKembali: EditText
    private lateinit var btnBayar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        // Inisialisasi komponen
        txtTotalHarga = findViewById(R.id.harga_total)
        inputBayar = findViewById(R.id.input_bayar)
        inputKembali = findViewById(R.id.input_kembali)
        btnBayar = findViewById(R.id.btn_bayar)

        // Ambil total harga dan order ID dari intent
        val jumlah = intent.getDoubleExtra("TOTAL_HARGA", 0.0)
        val orderId = intent.getIntExtra("ORDER_ID", 0)

        // Validasi apakah nilai yang diterima valid
        if (jumlah == 0.0 || orderId == 0) {
            Toast.makeText(this, "Data pembayaran tidak valid!", Toast.LENGTH_SHORT).show()
            finish() // Kembali ke halaman sebelumnya
            return
        }

        // Format harga ke bentuk Rupiah
        txtTotalHarga.text = formatRupiah(jumlah)

        // Tombol kembali ke transaksi
        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tombol bayar
        btnBayar.setOnClickListener {
            val bayar = inputBayar.text.toString().toDoubleOrNull()

            if (bayar == null || bayar < jumlah) {
                Toast.makeText(this, "Uang tidak cukup!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val kembali = bayar - jumlah
            inputKembali.setText(formatRupiah(kembali))  // Format kembalian ke Rupiah

            // Simpan data ke API
            simpanPembayaran(orderId, jumlah, bayar, kembali)
        }
    }

    private fun simpanPembayaran(orderId: Int, jumlah: Double, bayar: Double, kembali: Double) {
        val pembayaran = Pembayaran(orderId, jumlah, bayar, kembali)

        ApiClient.instance.createPembayaran(pembayaran).enqueue(object : Callback<ResponsePembayaran> {
            override fun onResponse(call: Call<ResponsePembayaran>, response: Response<ResponsePembayaran>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@PembayaranActivity, "Pembayaran berhasil!", Toast.LENGTH_SHORT).show()

                    // Pindah ke StrukActivity setelah sukses
                    val intent = Intent(this@PembayaranActivity, StrukActivity::class.java)
                    intent.putExtra("ORDER_ID", orderId)
                    intent.putExtra("TOTAL_HARGA", jumlah)
                    intent.putExtra("BAYAR", bayar)
                    intent.putExtra("KEMBALI", kembali)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@PembayaranActivity, "Gagal menyimpan pembayaran!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePembayaran>, t: Throwable) {
                Toast.makeText(this@PembayaranActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }

    // Fungsi untuk format angka ke Rupiah
    private fun formatRupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }
}
