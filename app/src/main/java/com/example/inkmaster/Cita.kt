package com.example.inkmaster

import com.google.gson.annotations.SerializedName

data class Cita(
    @SerializedName("idCita") val idCita: Int?,
    @SerializedName("fechaCita") val fechaCita: String,
    @SerializedName("horaCita") val horaCita: String,
    @SerializedName("idCliente") val idCliente: Int,
    @SerializedName("idEmpleado") val idEmpleado: Int
)
