package pmdm.bloggerapi.clientes.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LineaVentaClient {
    @GET("/ventas/pedido/{pedidoId}")
    suspend fun getLineasVentaByPedidoId(@Path("pedidoId") pedidoId: Long): Response<LineasVentaResponse>
}