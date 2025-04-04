package com.example.inkmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nombreEditText = findViewById<EditText>(R.id.editTextNombreCliente)
        val apellidoEditText = findViewById<EditText>(R.id.editTextApellidoCliente)
        val correoEditText = findViewById<EditText>(R.id.editTextCorreoCliente)
        val contrasenaEditText = findViewById<EditText>(R.id.editTextContrasenaCliente)
        val telefonoEditText = findViewById<EditText>(R.id.editTextTelefonoCliente)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val nombre = nombreEditText.text.toString().trim()
            val apellido = apellidoEditText.text.toString().trim()
            val correo = correoEditText.text.toString().trim()
            val contrasena = contrasenaEditText.text.toString().trim()
            val telefono = telefonoEditText.text.toString().trim()

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                registrarUsuario(nombre, apellido, correo, contrasena, telefono)
            }
        }
    }

    private fun registrarUsuario(nombre: String, apellido: String, correo: String, contrasena: String, telefono: String) {
        val request = RegisterRequest(nombre, apellido, correo, contrasena, telefono)

        val apiService = RetrofitInstance.api
        val call = apiService.registrarUsuario(request)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_LONG).show()
                    finish() // Cierra la actividad de registro
                } else {
                    Toast.makeText(applicationContext, "Error en el registro", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de conexión: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
