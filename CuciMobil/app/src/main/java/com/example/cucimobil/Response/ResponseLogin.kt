package com.example.cucimobil.Response

import com.example.cucimobil.Model.User

data class ResponseLogin(
    val message: String,
    val user: User?
)