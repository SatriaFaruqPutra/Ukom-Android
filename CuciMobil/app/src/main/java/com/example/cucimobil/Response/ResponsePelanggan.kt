package com.example.cucimobil.Response

import com.example.cucimobil.Model.Pelanggan

data class ResponsePelanggan(
    val success: Boolean,
    val message: String,
    val data: List<Pelanggan>
)

