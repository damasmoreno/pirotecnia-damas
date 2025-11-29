package pmdm.bloggerapi.articulos.data.network

import pmdm.bloggerapi.articulos.ui.model.Seccion

data class SeccionResponse(
    val id_Seccion: Long?,
    val nombre: String,
    val descripcion: String?,
    val imagenNombre: String?
) {
    fun toModel(): Seccion {
        return Seccion(
            ID_Seccion = id_Seccion,
            nombre = nombre,
            descripcion = descripcion,
            imagenNombre = imagenNombre
        )
    }
}