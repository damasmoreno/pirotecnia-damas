package pmdm.bloggerapi.articulos.data.network

import pmdm.bloggerapi.articulos.ui.model.Articulo


data class ArticuloResponse(
    val id_Articulo: Long?,
    val descripcion: String,
    val precio: String,
    val fechaAlta: String,
    val imagenNombre: String?



    ) {
    fun toModel(): Articulo {
        return Articulo(
            ID_Articulo = id_Articulo,
            descripcion = descripcion,
            precio = precio,
            fechaAlta = fechaAlta,
            imagenNombre = imagenNombre


        )
    }
}