package com.example.cucimobil

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponseLayanan
import com.example.cucimobil.adapter.LayananAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LayananActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layananAdapter: LayananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layanan)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerViewLayanan)
        recyclerView.layoutManager = LinearLayoutManager(this)
        layananAdapter = LayananAdapter(mutableListOf()) // Inisialisasi adapter dengan MutableList
        recyclerView.adapter = layananAdapter

        getDataLayanan()

        // Tombol kembali
        findViewById<ImageView>(R.id.btn_back).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getDataLayanan() {
        ApiClient.instance.getLayanan().enqueue(object : Callback<ResponseLayanan> {
            override fun onResponse(call: Call<ResponseLayanan>, response: Response<ResponseLayanan>) {
                if (response.isSuccessful && response.body() != null) {
                    val layananList = response.body()?.data ?: emptyList()

                    if (layananList.isNotEmpty()) {
                        layananAdapter.updateData(layananList) // Update adapter
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@LayananActivity, "Data layanan kosong", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LayananActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLayanan>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(this@LayananActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}
