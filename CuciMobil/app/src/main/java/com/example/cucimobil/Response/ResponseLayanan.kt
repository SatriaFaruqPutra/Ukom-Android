package com.example.cucimobil.Response

import com.example.cucimobil.Model.Layanan

data class ResponseLayanan(
    val success: Boolean,
    val message: String,
    val data: List<Layanan>
)