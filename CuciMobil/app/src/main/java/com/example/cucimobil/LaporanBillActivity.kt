package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Pembayaran
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Networking.ApiService
import com.example.cucimobil.Response.ResponsePelanggan
import com.example.cucimobil.Response.ResponsePembayaran
import com.example.cucimobil.adapter.PelangganAdapter
import com.example.cucimobil.adapter.PembayaranAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanBillActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pembayaranAdapter: PembayaranAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporanbill)

        recyclerView = findViewById(R.id.recyclerViewPembayaran)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getDataPembayaran()

        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun getDataPembayaran() {
        ApiClient.instance.getPembayaran().enqueue(object : Callback<ResponsePembayaran> {
            override fun onResponse(call: Call<ResponsePembayaran>, response: Response<ResponsePembayaran>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        if (responseBody.success) {
                            pembayaranAdapter = PembayaranAdapter(responseBody.data)
                            recyclerView.adapter = pembayaranAdapter
                        }
                    }
                } else {
                    Toast.makeText(this@LaporanBillActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponsePembayaran>, t: Throwable) {
                Toast.makeText(this@LaporanBillActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}
