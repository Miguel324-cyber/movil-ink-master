package com.example.inkmaster

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitCliente {
    // 🔹 Cliente HTTP con logging
    private val client: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // 🔹 Instancia de Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.137.250:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // 🔹 Instancia del servicio API
    val instance: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
