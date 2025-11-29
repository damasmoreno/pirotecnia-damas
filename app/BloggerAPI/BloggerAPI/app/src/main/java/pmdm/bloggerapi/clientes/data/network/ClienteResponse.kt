package pmdm.bloggerapi.clientes.data.network


import pmdm.bloggerapi.clientes.ui.model.Cliente

data class ClienteResponse(
    val id: Long?,
    val Nombre: String,
    val nacionalidad: String,
    val nif: String,
    val fechaNacimiento: String,
    val correo_electronico: String,
    val password: String



    ) {
    fun toModel(): Cliente {
        return Cliente(
            id = id,
            Nombre = Nombre ,
            nacionalidad = nacionalidad,
            nif = nif,
            fechaNacimiento = fechaNacimiento,
            correo_electronico = correo_electronico,
            password = password


        )
    }
}