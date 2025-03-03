package com.example.cucimobil.Model

data class Order(
    val tanggal: String,
    val pelanggan_id: Int,
    val layanan_id: Int,
    val harga: Int
)
