package pmdm.bloggerapi.articulos.data.network

import com.google.gson.annotations.SerializedName

data class PedidoRequest(
    @SerializedName("ID_Cliente")
    val ID_Cliente: Long,

    @SerializedName("Total")
    val Total: Double,

    @SerializedName("Entregado")
    val Entregado: Boolean = false
)

