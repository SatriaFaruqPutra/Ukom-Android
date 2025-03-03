package com.example.cucimobil

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cucimobil.Networking.ApiClient
import com.example.cucimobil.Response.LoginRequest
import com.example.cucimobil.Response.ResponseUser
import com.example.cucimobil.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Tombol kembali ke Home
        binding.img5.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Pindah ke halaman registrasi
        binding.textView3.setOnClickListener {
            startActivity(Intent(this, RegistrasiActivity::class.java))
        }

        // Tombol login
        binding.signinBtn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            return
        }

        val request = LoginRequest(email, password)

        ApiClient.instance.login(request).enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true && loginResponse.data != null) {
                        val namaUser = loginResponse.data.nama // Ambil hanya nama

                        // Simpan nama ke SharedPreferences
                        val editor = sharedPreferences.edit()
                        editor.putString("nama", namaUser)
                        editor.apply()

                        Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()

                        val intent = Intent(this@LoginActivity, ProfilActivity::class.java)
                        intent.putExtra("NAMA_USER", namaUser) // Kirim nama ke ProfilActivity
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this@LoginActivity, "Login Gagal: ${loginResponse?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Terjadi kesalahan saat login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
                Toast.makeText(this@LoginActivity, "Gagal terhubung ke server!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
