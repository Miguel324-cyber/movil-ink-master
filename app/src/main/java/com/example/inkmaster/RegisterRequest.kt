package com.example.inkmaster

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val nombreCliente: String,
    val apellidoCliente: String,
    val correoCliente: String,
    val contrasenaCliente: String,
    val telefonoCliente: String
)

