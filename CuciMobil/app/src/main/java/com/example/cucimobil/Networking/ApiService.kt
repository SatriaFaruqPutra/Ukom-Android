package com.example.cucimobil.Networking

import com.example.cucimobil.Model.Layanan
import com.example.cucimobil.Model.Order
import com.example.cucimobil.Model.Pelanggan
import com.example.cucimobil.Model.Pembayaran
import com.example.cucimobil.Response.LoginRequest
import com.example.cucimobil.Response.RegisRequest
import com.example.cucimobil.Response.ResponseLayanan
import com.example.cucimobil.Response.ResponseOrder
import com.example.cucimobil.Response.ResponsePelanggan
import com.example.cucimobil.Response.ResponsePembayaran
import com.example.cucimobil.Response.ResponseUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("pelanggan") // Sesuaikan dengan endpoint API Laravel
    fun getPelanggan(): Call<ResponsePelanggan>
    @POST("pelanggan")
    fun tambahPelanggan(@Body pelanggan: Pelanggan): Call<ResponsePelanggan>


    @GET("layanan") // Sesuaikan dengan endpoint API Laravel
    fun getLayanan(): Call<ResponseLayanan>
    @POST("layanan")
    fun tambahLayanan(@Body layanan: Layanan): Call<ResponseLayanan>

    @GET("user") // Sesuaikan dengan endpoint API Laravel
    fun getUser(): Call<ResponseUser>
    @POST("register")
    fun register(@Body request: RegisRequest): Call<ResponseUser>
    @POST("login")
    fun login(@Body request: LoginRequest): Call<ResponseUser>

    fun getPembayaran(): Call<ResponsePembayaran>
    @GET("pembayaran/{id}")
    fun getPembayaranById(@Path("id") id: Int): Call<ResponsePembayaran>
    @POST("pembayaran")
    fun createPembayaran(@Body pembayaran: Pembayaran): Call<ResponsePembayaran>

    @GET("order")
    fun getOrder(): Call<ResponseOrder>
    @POST("order")
    fun createOrder(@Body order: Order): Call<ResponseOrder>

}
