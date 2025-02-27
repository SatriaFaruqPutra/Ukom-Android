package com.example.cleandrive

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cleandrive.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var signin_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnBackLoginListener()
        txtRegisListener()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinBtn.setOnClickListener(View.OnClickListener {
            if (binding.email.text.toString() == "user" && binding.password.text.toString() == "1234"){
                Toast.makeText(this, "Login Sukses", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Login Gagal", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun btnBackLoginListener() {
        val btnBackLogin = findViewById<ImageView>(R.id.img_5)
        btnBackLogin.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun txtRegisListener() {
        val txtRegis = findViewById<TextView>(R.id.textView3)
        txtRegis.setOnClickListener{
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }
    }
}