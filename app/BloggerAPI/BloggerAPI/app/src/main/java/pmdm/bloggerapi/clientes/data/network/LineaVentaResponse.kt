package pmdm.bloggerapi.clientes.data.network

import com.google.gson.annotations.SerializedName

data class LineasVentaResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("lineasVenta")
    val lineasVenta: List<LineaVentaResponse> = emptyList()
)

data class LineaVentaResponse(
    @SerializedName("ID_Venta")
    val idVenta: Long? = null,

    @SerializedName("ID_Articulo")
    val idArticulo: Long? = null,

    @SerializedName("Unidades")
    val unidades: Int? = null,

    @SerializedName("Precio")
    val precio: Double? = null,

    @SerializedName("Total")
    val total: Double? = null,

    @SerializedName("ID_Pedido")
    val idPedido: Long? = null,


    @SerializedName("descripcionArticulo")
    val descripcionArticulo: String? = null
) {
    fun toModel(): pmdm.bloggerapi.clientes.ui.model.LineaVenta {
        return pmdm.bloggerapi.clientes.ui.model.LineaVenta(
            idVenta = idVenta ?: 0L,
            idArticulo = idArticulo ?: 0L,
            descripcionArticulo = descripcionArticulo ?: "Artículo ${idArticulo}",
            unidades = unidades ?: 0,
            precio = precio ?: 0.0,
            total = total ?: 0.0,
            idPedido = idPedido ?: 0L
        )
    }
}