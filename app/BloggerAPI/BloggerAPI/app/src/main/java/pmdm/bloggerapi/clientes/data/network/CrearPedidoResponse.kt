package pmdm.bloggerapi.clientes.data.network

import com.google.gson.annotations.SerializedName

data class CrearPedidoResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("pedido")
    val pedido: PedidoResponse? = null
)

