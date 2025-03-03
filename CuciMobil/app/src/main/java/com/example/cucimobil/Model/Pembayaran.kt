package com.example.cucimobil.Model

data class Pembayaran(
        val orderId: Int,
        val jumlah: Double,
        val bayar: Double,
        val kembali: Double
)
