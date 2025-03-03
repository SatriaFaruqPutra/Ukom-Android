package com.example.cucimobil.Response

import com.example.cucimobil.Model.Pembayaran

data class ResponsePembayaran(
    val success: Boolean,
    val message: String,
    val data: List<Pembayaran>
)