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
import com.example.cleandrive.databinding.ActivityRegistrasiBinding

class RegistrasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding

    lateinit var username: EditText
    lateinit var phone: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var signup_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrasi)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnBackRegisListener()
        txtLoginListener()

        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener(View.OnClickListener {
            if (binding.username.text.toString() == "user" && binding.phone.text.toString() == "0852" && binding.email.text.toString() == "email" && binding.password.text.toString() == "1234"){
                Toast.makeText(this, "Registrasi Sukses", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Ragistrasi Gagal", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun btnBackRegisListener() {
        val btnBackRegis = findViewById<ImageView>(R.id.img_5)
        btnBackRegis.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
    private fun txtLoginListener() {
        val txtLogin = findViewById<TextView>(R.id.textView3)
        txtLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}