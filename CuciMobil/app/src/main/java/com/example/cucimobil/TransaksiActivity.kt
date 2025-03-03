package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cucimobil.Model.Order
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponseOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody

class TransaksiActivity : AppCompatActivity() {

    private lateinit var inputTanggal: EditText
    private lateinit var inputPelanggan: EditText
    private lateinit var inputLayanan: EditText
    private lateinit var inputHarga: EditText
    private lateinit var btnBayar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)

        inputTanggal = findViewById(R.id.input_tanggal)
        inputPelanggan = findViewById(R.id.input_pelanggan)
        inputLayanan = findViewById(R.id.input_layanan)
        inputHarga = findViewById(R.id.input_harga)
        btnBayar = findViewById(R.id.btn_bayar)

        btnBayar.setOnClickListener {
            simpanOrder()
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun simpanOrder() {
        val tanggal = inputTanggal.text.toString().trim()
        val pelangganId = inputPelanggan.text.toString().toIntOrNull()
        val layananId = inputLayanan.text.toString().toIntOrNull()
        val harga = inputHarga.text.toString().toIntOrNull()

        // Validasi input
        if (tanggal.isEmpty() || pelangganId == null || layananId == null || harga == null) {
            Toast.makeText(this, "Harap isi semua data dengan benar!", Toast.LENGTH_SHORT).show()
            return
        }

        val order = Order(tanggal, pelangganId, layananId, harga)

        ApiClient.instance.createOrder(order).enqueue(object : Callback<ResponseOrder> {
            override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@TransaksiActivity, "Order berhasil disimpan!", Toast.LENGTH_SHORT).show()

                    // **Pindah ke halaman Pembayaran**
                    val intent = Intent(this@TransaksiActivity, PembayaranActivity::class.java)
                    intent.putExtra("TOTAL_HARGA", harga)  // Kirim total harga ke halaman pembayaran
                    startActivity(intent)
                    finish()  // Menutup halaman transaksi agar tidak bisa kembali dengan back button
                } else {
                    Toast.makeText(this@TransaksiActivity, "Gagal menyimpan order", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                Toast.makeText(this@TransaksiActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}
