package com.example.cleandrive

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnHome()
        btnBooking()
        btnProfil()
    }
    private fun btnHome() {
        val btnH = findViewById<ImageView>(R.id.img_home)
        btnH.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun btnBooking() {
        val btnB = findViewById<ImageView>(R.id.img_book)
        btnB.setOnClickListener{
            startActivity(Intent(this, BookingActivity::class.java))
        }
    }

    private fun btnProfil() {
        val btnP = findViewById<ImageView>(R.id.img_profil)
        btnP.setOnClickListener{
            startActivity(Intent(this, ProfilActivity::class.java))
        }
    }
}