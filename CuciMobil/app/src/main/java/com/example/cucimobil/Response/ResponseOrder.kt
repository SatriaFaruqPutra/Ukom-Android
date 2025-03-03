package com.example.cucimobil.Response

import com.example.cucimobil.Model.Order

data class ResponseOrder(
    val success: Boolean,
    val data: List<Order>
)
