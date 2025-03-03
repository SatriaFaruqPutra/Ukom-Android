package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.RegisRequest
import com.example.cucimobil.Response.ResponseUser
import com.example.cucimobil.databinding.ActivityRegistrasiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        // Tombol Registrasi
        binding.signupBtn.setOnClickListener {
            registerUser()
        }

        // Tombol kembali ke Home
        binding.img5.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Pindah ke halaman login
        binding.textView3.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser() {
        val nama = binding.username.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        val request = RegisRequest(nama, email, password)
        ApiClient.instance.register(request).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse?.success == true) {
                        Toast.makeText(this@RegistrasiActivity, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()

                        // Pindah ke halaman login setelah registrasi
                        startActivity(Intent(this@RegistrasiActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@RegistrasiActivity, "Registrasi Gagal: ${registerResponse?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@RegistrasiActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
                Toast.makeText(this@RegistrasiActivity, "Gagal terhubung ke server!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
