package com.example.cucimobil

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cucimobil.Model.Layanan
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponseLayanan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahLayananActivity : AppCompatActivity() {

    private lateinit var inputNama: EditText
    private lateinit var inputJenis: EditText
    private lateinit var inputDeskripsi: EditText
    private lateinit var inputHarga: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambahlayanan)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputNama = findViewById(R.id.input_nama)
        inputJenis = findViewById(R.id.input_jenis)
        inputDeskripsi = findViewById(R.id.input_deskripsi)
        inputHarga = findViewById(R.id.input_harga)
        btnSimpan = findViewById(R.id.btn_simpan)

        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Aksi tombol simpan
        btnSimpan.setOnClickListener {
            tambahLayanan()
        }
    }

    private fun tambahLayanan() {
        val namaLayanan = inputNama.text.toString().trim()
        val jenisKendaraan = inputJenis.text.toString().trim()
        val deskripsi = inputDeskripsi.text.toString().trim()
        val hargaStr = inputHarga.text.toString().trim()

        // Validasi input tidak boleh kosong
        if (namaLayanan.isEmpty() || jenisKendaraan.isEmpty() || deskripsi.isEmpty() || hargaStr.isEmpty()) {
            Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
            return
        }

        // Validasi harga harus berupa angka
        val harga = hargaStr.toDoubleOrNull()
        if (harga == null || harga <= 0) {
            Toast.makeText(this, "Harga harus berupa angka yang valid!", Toast.LENGTH_SHORT).show()
            return
        }

        // Buat objek Layanan
        val layananRequest = Layanan(namaLayanan, jenisKendaraan, harga, deskripsi)

        // Panggil API untuk menambahkan layanan
        ApiClient.instance.tambahLayanan(layananRequest).enqueue(object : Callback<ResponseLayanan> {
            override fun onResponse(call: Call<ResponseLayanan>, response: Response<ResponseLayanan>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@TambahLayananActivity, "Layanan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    finish() // Tutup activity setelah berhasil
                } else {
                    Toast.makeText(this@TambahLayananActivity, "Gagal menambahkan layanan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseLayanan>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@TambahLayananActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
