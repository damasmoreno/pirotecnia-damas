package pmdm.bloggerapi.clientes.ui.model




data class Cliente(
    val id: Long?,
    val Nombre: String,
    val nacionalidad: String,
    val nif: String,
    val fechaNacimiento: String,
    val correo_electronico: String,
    val password: String
)