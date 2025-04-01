package com.example.inkmaster

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitCliente {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.137.250:8000") // 👉 Cambia por la IP o dominio de tu Laravel
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!
    }

    // 🔹 Agrega esta parte para la instancia del servicio
    val instance: ApiService by lazy {
        getClient().create(ApiService::class.java)
    }
}
