package com.example.cucimobil

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cucimobil.Model.Pelanggan
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponsePelanggan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TambahPelangganActivity : AppCompatActivity() {

    private lateinit var inputNama: EditText
    private lateinit var inputAlamat: EditText
    private lateinit var inputTelepon: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambahpelanggan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi View
        inputNama = findViewById(R.id.input_nama)
        inputTelepon = findViewById(R.id.input_telepon)
        inputAlamat = findViewById(R.id.input_alamat)
        btnSimpan = findViewById(R.id.btn_simpan)

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        btnSimpan.setOnClickListener {
            simpanPelanggan()
        }
    }

    private fun simpanPelanggan() {
        val nama = inputNama.text.toString().trim()
        val noHp = inputTelepon.text.toString().trim()
        val alamat = inputAlamat.text.toString().trim()

        if (nama.isEmpty() || noHp.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val pelanggan = Pelanggan(nama = nama, alamat = alamat,  no_hp = noHp) // Objek sesuai Model

        // Kirim data ke server
        ApiClient.instance.tambahPelanggan(pelanggan).enqueue(object : Callback<ResponsePelanggan> {
            override fun onResponse(call: Call<ResponsePelanggan>, response: Response<ResponsePelanggan>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(this@TambahPelangganActivity, "Pelanggan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke halaman sebelumnya
                } else {
                    Toast.makeText(this@TambahPelangganActivity, "Gagal menambah pelanggan!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePelanggan>, t: Throwable) {
                Toast.makeText(this@TambahPelangganActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}
