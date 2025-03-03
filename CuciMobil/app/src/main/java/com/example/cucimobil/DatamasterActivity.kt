package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DatamasterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datamaster)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cardPelanggan = findViewById<CardView>(R.id.pelanggan)
        val cardLayanan = findViewById<CardView>(R.id.layanan)

        // Klik CardView Pelanggan
        cardPelanggan.setOnClickListener {
            val intent = Intent(this, PelangganActivity::class.java)
            startActivity(intent)
        }

        // Klik CardView Layanan
        cardLayanan.setOnClickListener {
            val intent = Intent(this, LayananActivity::class.java)
            startActivity(intent)
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


    }
}