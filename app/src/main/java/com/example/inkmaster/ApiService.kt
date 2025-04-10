package com.example.inkmaster

import CitaEmpleadoResponse
import CitasEmpleadoResponseWrapper
import IdEmpleadoRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")  // 👈 Ajusta esto según la ruta de tu API en Laravel
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("cliente") // No pongas /api/ aquí, ya está en baseUrl
        fun registrarUsuario(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("cita")
    fun crearCita(@Body citaRequest: CitaRequest): Call<CitaResponse>

    @POST("citasEmpleado")
    fun obtenerCitasEmpleado(@Body request: IdEmpleadoRequest): Call<CitasEmpleadoResponseWrapper>


}


