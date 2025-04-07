package com.example.inkmaster

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log


class ReservaCitaActivity : AppCompatActivity() {

    private lateinit var fechaEditText: EditText
    private lateinit var horaEditText: EditText
    private lateinit var empleadoEditText: EditText
    private lateinit var botonReservar: Button
    private var idCliente: Int = -1 // se pasará desde el login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_cita)

        fechaEditText = findViewById(R.id.editTextFecha)
        horaEditText = findViewById(R.id.editTextHora)
        empleadoEditText = findViewById(R.id.editTextEmpleado)
        botonReservar = findViewById(R.id.botonReservar)

        // 🔐 Recibir idCliente desde el Login
        idCliente = intent.getIntExtra("idCliente", -1)

        botonReservar.setOnClickListener {
            val fecha = fechaEditText.text.toString()
            val hora = horaEditText.text.toString()
            val idEmpleado = empleadoEditText.text.toString().toIntOrNull()

            if (fecha.isEmpty() || hora.isEmpty() || idEmpleado == null) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val citaRequest = CitaRequest(
                fechaCita = fecha,
                horaCita = hora,
                idCliente = idCliente,
                idEmpleado = idEmpleado
            )

            RetrofitCliente.instance.crearCita(citaRequest).enqueue(object : Callback<CitaResponse> {
                override fun onResponse(call: Call<CitaResponse>, response: Response<CitaResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Cita reservada exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Log.e("ReservaCita", "Código: ${response.code()} - Error: ${response.errorBody()?.string()}")
                        Toast.makeText(applicationContext, "Error al reservar cita", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<CitaResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
