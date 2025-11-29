package pmdm.bloggerapi.articulos.data.network

import com.google.gson.annotations.SerializedName

data class LineaVentaRequest(
    @SerializedName("ID_Articulo")
    val ID_Articulo: Long,

    @SerializedName("Unidades")
    val Unidades: Int,

    @SerializedName("Precio")
    val Precio: Double,

    @SerializedName("Total")
    val Total: Double,

    @SerializedName("ID_Pedido")
    val ID_Pedido: Long
)
