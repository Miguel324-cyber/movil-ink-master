import com.google.gson.annotations.SerializedName

data class CitaEmpleadoResponse(
    val idCita: Int,
    val fechaCita: String,
    val horaCita: String,
    val cliente: ClienteInfo
)


data class ClienteInfo(
    val idCliente: Int,
    @SerializedName("nombreCliente") val nombre: String,  // 👈 Corregido
    @SerializedName("correoCliente") val correo: String   // 👈 Corregido
)



