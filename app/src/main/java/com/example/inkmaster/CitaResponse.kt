package com.example.inkmaster

data class CitaResponse(
    val status: Int,
    val message: String?,
    val cita: Cita? // Puedes crear un modelo Cita si lo deseas
)
