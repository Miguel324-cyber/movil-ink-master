package com.example.inkmaster
import com.example.inkmaster.Cliente

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Int,
    @SerializedName("token") val token: String?,
    @SerializedName("cliente") val cliente: Cliente?,
    @SerializedName("message") val message: String?
)    
