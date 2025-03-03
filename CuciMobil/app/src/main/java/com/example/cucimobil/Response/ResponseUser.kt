package com.example.cucimobil.Response

import com.example.cucimobil.Model.User

data class ResponseUser (
    val success: Boolean,
    val message: String,
    val data: User?
)

