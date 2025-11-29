package pmdm.bloggerapi.clientes.ui.model





data class LineaVenta(
    val idVenta: Long,
    val idArticulo: Long,
    val descripcionArticulo: String, // Cambiado de nombreArticulo
    val unidades: Int,
    val precio: Double,
    val total: Double,
    val idPedido: Long
)