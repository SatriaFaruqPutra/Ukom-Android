package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi CardView
        val cardDataMaster = findViewById<CardView>(R.id.cardViewDataMaster)
        val cardTransaksi = findViewById<CardView>(R.id.cardViewTransaksi)
        val cardRiwayat = findViewById<CardView>(R.id.cardViewRiwayat)
        val cardProfil = findViewById<CardView>(R.id.cardViewProfil)

        // Mengatur klik listener dengan fungsi helper
        cardDataMaster.setOnClickListener { navigateTo(DatamasterActivity::class.java) }
        cardTransaksi.setOnClickListener { navigateTo(TransaksiActivity::class.java) }
        cardRiwayat.setOnClickListener { navigateTo(RiwayatTransaksiActivity2::class.java) }
        cardProfil.setOnClickListener { navigateTo(ProfilActivity::class.java) }
    }

    // Fungsi helper untuk pindah halaman
    private fun navigateTo(destination: Class<*>) {
        startActivity(Intent(this, destination))
    }
}

