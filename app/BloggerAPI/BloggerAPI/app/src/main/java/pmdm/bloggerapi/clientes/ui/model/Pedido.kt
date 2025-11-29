package pmdm.bloggerapi.clientes.ui.model

data class Pedido(
    val id: Long,
    val clienteId: Long,
    val fechaPedido: String? = null,
    val total: Double? = null,
    val entregado: Boolean? = null
)