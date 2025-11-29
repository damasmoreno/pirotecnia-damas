package pmdm.bloggerapi.clientes.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PedidoClient {
    /**
     * Obtiene los pedidos de un cliente específico
     * GET /pedidos/mis-pedidos/{clienteId}
     * @param clienteId ID del cliente
     * @return Response con lista de pedidos del cliente
     */
    @GET("/pedidos/mis-pedidos/{clienteId}")
    suspend fun getPedidosByClienteId(@Path("clienteId") clienteId: Long): Response<PedidosResponse>
}