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

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val registerButton = findViewById<Button>(R.id.button2)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email, password)
            val call = RetrofitCliente.instance.login(loginRequest)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse = response.body()!!

                        if (loginResponse.status == 200) {
                            Toast.makeText(applicationContext, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                            when (loginResponse.tipo) {
                                "cliente" -> {
                                    val intent = Intent(applicationContext, ReservaCitaActivity::class.java)
                                    intent.putExtra("idCliente", loginResponse.cliente?.idCliente ?: -1)
                                    startActivity(intent)
                                    finish()
                                }

                                "empleado" -> {
                                    val intent = Intent(applicationContext, CitasEmpleadoActivity::class.java)
                                    intent.putExtra("idEmpleado", loginResponse.empleado?.idEmpleado ?: -1)
                                    startActivity(intent)
                                    finish()
                                }

                                else -> {
                                    Toast.makeText(applicationContext, "Tipo de usuario desconocido", Toast.LENGTH_SHORT).show()
                                }
                            }

                        } else {
                            Toast.makeText(applicationContext, loginResponse.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Error en el servidor", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
