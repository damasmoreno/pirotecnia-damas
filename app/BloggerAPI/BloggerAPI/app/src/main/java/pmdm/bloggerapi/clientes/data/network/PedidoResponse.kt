package pmdm.bloggerapi.clientes.data.network

import com.google.gson.annotations.SerializedName
import pmdm.bloggerapi.clientes.ui.model.Pedido

data class PedidosResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("pedidos")
    val pedidos: List<PedidoResponse> = emptyList()
)

data class PedidoResponse(
    @SerializedName("id_Pedido")
    val idPedido: Long? = null,

    @SerializedName("id_Cliente")
    val idCliente: Long? = null,

    @SerializedName("fecha_pedido")
    val fechaPedido: String? = null,

    @SerializedName("total")
    val total: Double? = null,

    @SerializedName("entregado")
    val entregado: Boolean? = null
) {
    fun toModel(): Pedido {
        return Pedido(
            id = idPedido ?: 0L,
            clienteId = idCliente ?: 0L,
            fechaPedido = formatFecha(fechaPedido),
            total = total,
            entregado = entregado
        )
    }

    private fun formatFecha(fechaOriginal: String?): String? {
        return if (fechaOriginal.isNullOrEmpty()) {
            null
        } else {
            try {
                fechaOriginal.substringBefore("T")
            } catch (e: Exception) {
                fechaOriginal
            }
        }
    }
}