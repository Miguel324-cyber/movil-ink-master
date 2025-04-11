package com.example.inkmaster

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("token") val token: String?,
    @SerializedName("tipo") val tipo: String, // ← NUEVO: "cliente" o "empleado"
    @SerializedName("cliente") val cliente: Cliente?, // ← se usa solo si es cliente
    @SerializedName("empleado") val empleado: Empleado?, // ← se usa solo si es empleado
    @SerializedName("message") val message: String?
)
