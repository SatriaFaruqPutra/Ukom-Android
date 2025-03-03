package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RiwayatTransaksiActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_riwayattransaksi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack = findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            val intent = Intent(this, DatamasterActivity::class.java)
            startActivity(intent)
            finish()
        }

        val transaksiCard: CardView = findViewById(R.id.transaki)
        val pembayaranCard: CardView = findViewById(R.id.pembayaran)

        transaksiCard.setOnClickListener {
            val intent = Intent(this, LaporanTransaksiActivity::class.java)
            startActivity(intent)
        }

        pembayaranCard.setOnClickListener {
            val intent = Intent(this, LaporanBillActivity::class.java)
            startActivity(intent)
        }
    }
}