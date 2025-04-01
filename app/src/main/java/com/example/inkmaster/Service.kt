package com.example.inkmaster

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {

    @GET("Cliente/{idCliente}")
    fun getUsers(@Path("idCliente") userId: Int):Call<ApiResponse>

    @POST("login") // Ajusta esto según la ruta de tu API Laravel
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}