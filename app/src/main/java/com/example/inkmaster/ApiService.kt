package com.example.inkmaster

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")  // 👈 Ajusta esto según la ruta de tu API en Laravel
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
