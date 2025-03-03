package com.example.cucimobil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StrukActivity : AppCompatActivity() {

    private lateinit var textNamaPelanggan: TextView
    private lateinit var textJenisLayanan: TextView
    private lateinit var textHarga: TextView
    private lateinit var textTotal: TextView
    private lateinit var textBayar: TextView
    private lateinit var textKembali: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_struk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inisialisasi komponen
        textNamaPelanggan = findViewById(R.id.textNamaPelanggan)
        textJenisLayanan = findViewById(R.id.textJenisLayanan)
        textHarga = findViewById(R.id.textHarga)
        textTotal = findViewById(R.id.textTotal)
        textBayar = findViewById(R.id.textBayar)
        textKembali = findViewById(R.id.textKembali)

        // Ambil data dari intent
        val namaPelanggan = intent.getStringExtra("NAMA_PELANGGAN") ?: "Tidak Diketahui"
        val jenisLayanan = intent.getStringExtra("JENIS_LAYANAN") ?: "Tidak Diketahui"
        val harga = intent.getDoubleExtra("HARGA", 0.0)
        val totalHarga = intent.getDoubleExtra("TOTAL_HARGA", 0.0)
        val bayar = intent.getDoubleExtra("BAYAR", 0.0)
        val kembali = intent.getDoubleExtra("KEMBALI", 0.0)

        // Set nilai ke TextView
        textNamaPelanggan.text = namaPelanggan
        textJenisLayanan.text = jenisLayanan
        textHarga.text = "Rp$harga"
        textTotal.text = "Rp$totalHarga"
        textBayar.text = "Rp$bayar"
        textKembali.text = "Rp$kembali"

        // Inisialisasi Button
        val btnSelesai = findViewById<Button>(R.id.btn_tambah)
        btnSelesai.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
