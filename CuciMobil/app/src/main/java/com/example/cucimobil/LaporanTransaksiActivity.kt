package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Order
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.ResponseOrder
import com.example.cucimobil.adapter.OrderAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanTransaksiActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporantransaksi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        recyclerView = findViewById(R.id.recyclerViewOrder)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getDataOrder()
    }

    private fun getDataOrder() {
        ApiClient.instance.getOrder().enqueue(object : Callback<ResponseOrder> {
            override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        if (responseBody.success) {
                            orderAdapter = OrderAdapter(responseBody.data)
                            recyclerView.adapter = orderAdapter
                        }
                    }
                } else {
                    Toast.makeText(this@LaporanTransaksiActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                Toast.makeText(this@LaporanTransaksiActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_ERROR", t.message ?: "Unknown error")
            }
        })
    }
}
