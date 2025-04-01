package com.example.inkmaster

import com.google.gson.annotations.SerializedName

data class Cliente(
    @SerializedName("idCliente") val idCliente: Int,
    @SerializedName("correoCliente") val correoCliente: String,
    @SerializedName("contrasenaCliente") val contrasenaCliente: String,
    @SerializedName("nombreCliente") val nombreCliente: String,
    @SerializedName("telefonoCliente") val telefonoCliente: Long

)

data class ApiResponse(
    @SerializedName("cliente") val cliente: Cliente,
    @SerializedName("status") val status: Int
)