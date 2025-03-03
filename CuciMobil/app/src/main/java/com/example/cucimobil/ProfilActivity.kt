package com.example.cucimobil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBack: ImageView = findViewById(R.id.btn_back)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val namaUser = sharedPreferences.getString("nama", "Guest")

        // Set nama di TextView
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        tvUsername.text = namaUser

        val btnLogout = findViewById<Button>(R.id.btn_sign_in)
        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()  // Hapus data login

        // Pindah ke LoginActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}