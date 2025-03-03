package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Pelanggan
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponsePelanggan
import com.example.cucimobil.adapter.PelangganAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PelangganActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pelangganAdapter: PelangganAdapter
    private var pelangganList = mutableListOf<Pelanggan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pelanggan)

        // Setup window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPelanggan)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi Adapter
        pelangganAdapter = PelangganAdapter(pelangganList)
        recyclerView.adapter = pelangganAdapter

        // Ambil Data Pelanggan
        getDataPelanggan()

        // Tombol Back
        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val btnTambah: Button = findViewById(R.id.btn_tambah)
        btnTambah.setOnClickListener {
            val intent = Intent(this, TambahPelangganActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getDataPelanggan() {
        ApiClient.instance.getPelanggan().enqueue(object : Callback<ResponsePelanggan> {
            override fun onResponse(call: Call<ResponsePelanggan>, response: Response<ResponsePelanggan>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    response.body()?.data?.let { data ->
                        pelangganList.clear() // Bersihkan list sebelum menambah data baru
                        pelangganList.addAll(data)
                        pelangganAdapter.notifyDataSetChanged() // Perbarui adapter
                    }
                } else {
                    Toast.makeText(this@PelangganActivity, "Gagal mengambil data pelanggan!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePelanggan>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}") // Tambahkan log error untuk debugging
                Toast.makeText(this@PelangganActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
