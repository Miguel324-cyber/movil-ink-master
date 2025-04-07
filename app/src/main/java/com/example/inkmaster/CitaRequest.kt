package com.example.inkmaster

data class CitaRequest(
    val fechaCita: String,
    val horaCita: String,
    val idCliente: Int,
    val idEmpleado: Int
)
