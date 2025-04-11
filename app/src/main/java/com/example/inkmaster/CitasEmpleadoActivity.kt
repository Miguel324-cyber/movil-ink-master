package com.example.inkmaster

import CitasEmpleadoResponseWrapper
import IdEmpleadoRequest
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitasEmpleadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas_empleado)

        val idEmpleado = intent.getIntExtra("idEmpleado", -1)
        val tabla = findViewById<TableLayout>(R.id.tableLayoutCitas)

        if (idEmpleado == -1) {
            Toast.makeText(this, "ID de empleado inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val call = RetrofitCliente.instance.obtenerCitasEmpleado(IdEmpleadoRequest(idEmpleado))

        call.enqueue(object : Callback<CitasEmpleadoResponseWrapper> {
            override fun onResponse(
                call: Call<CitasEmpleadoResponseWrapper>,
                response: Response<CitasEmpleadoResponseWrapper>
            ) {
                if (response.isSuccessful) {
                    val citas = response.body()?.citas ?: emptyList()
                    citas.forEach { cita ->
                        agregarFila(
                            tabla,
                            cita.idCita,
                            cita.fechaCita,
                            cita.horaCita,
                            cita.cliente.nombre
                        )
                    }
                } else {
                    Toast.makeText(
                        this@CitasEmpleadoActivity,
                        "No se pudo obtener las citas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<CitasEmpleadoResponseWrapper>, t: Throwable) {
                Toast.makeText(
                    this@CitasEmpleadoActivity,
                    "Error de conexión: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun agregarFila(tabla: TableLayout, id: Int, fecha: String, hora: String, cliente: String) {
        val fila = TableRow(this)

        val txtId = TextView(this)
        txtId.text = id.toString()
        txtId.setTextColor(Color.BLACK) // Cambiado a negro
        txtId.setPadding(8, 8, 8, 8)
        txtId.gravity = Gravity.CENTER

        val txtFecha = TextView(this)
        txtFecha.text = fecha
        txtFecha.setTextColor(Color.BLACK) // Cambiado a negro
        txtFecha.setPadding(8, 8, 8, 8)
        txtFecha.gravity = Gravity.CENTER

        val txtHora = TextView(this)
        txtHora.text = hora
        txtHora.setTextColor(Color.BLACK) // Cambiado a negro
        txtHora.setPadding(8, 8, 8, 8)
        txtHora.gravity = Gravity.CENTER

        val txtCliente = TextView(this)
        txtCliente.text = cliente
        txtCliente.setTextColor(Color.BLACK) // Cambiado a negro
        txtCliente.setPadding(8, 8, 8, 8)
        txtCliente.gravity = Gravity.CENTER

        fila.addView(txtId)
        fila.addView(txtFecha)
        fila.addView(txtHora)
        fila.addView(txtCliente)

        tabla.addView(fila)
    }
}
