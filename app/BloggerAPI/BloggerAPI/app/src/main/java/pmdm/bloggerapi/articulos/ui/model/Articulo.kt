package pmdm.bloggerapi.articulos.ui.model


data class Articulo(
    val ID_Articulo: Long?,
    val descripcion: String,
    val precio: String,
    val fechaAlta: String,
    val imagenNombre: String?
)