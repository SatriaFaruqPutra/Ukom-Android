package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cucimobil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnLoginListener()
        btnRegisListener()
    }
    private fun btnLoginListener() {
        val btnLogin = findViewById<Button>(R.id.btn_signin)
        btnLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun btnRegisListener() {
        val btnRegis = findViewById<Button>(R.id.btn_signup)
        btnRegis.setOnClickListener{
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }
    }
}