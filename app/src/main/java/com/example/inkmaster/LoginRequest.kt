package com.example.inkmaster
import com.example.inkmaster.Cliente

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correoCliente") val correo: String,
    @SerializedName("contrasenaCliente") val contrasena: String
)
