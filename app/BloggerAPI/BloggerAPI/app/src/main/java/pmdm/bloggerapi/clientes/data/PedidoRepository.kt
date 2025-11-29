package pmdm.bloggerapi.clientes.data

import pmdm.bloggerapi.clientes.data.network.PedidoClient
import pmdm.bloggerapi.clientes.ui.model.Pedido
import javax.inject.Inject

class PedidoRepository @Inject constructor(
    private val pedidoClient: PedidoClient
) {
    /**
     * Obtiene los pedidos de un cliente específico
     * @param clienteId ID del cliente
     * @return Result con lista de pedidos o error
     */
    suspend fun getPedidosByClienteId(clienteId: Long): Result<List<Pedido>> {
        return try {


            val response = pedidoClient.getPedidosByClienteId(clienteId)



            if (response.isSuccessful) {
                val pedidosResponse = response.body()


                if (pedidosResponse != null) {



                    pedidosResponse.pedidos.forEachIndexed { index, pedidoResp ->

                    }

                    if (pedidosResponse.success == true) {
                        val pedidos = pedidosResponse.pedidos.map { pedidoResp ->
                            val pedidoModel = pedidoResp.toModel()
                            pedidoModel
                        }
                        Result.success(pedidos)
                    } else {
                        val errorMsg = pedidosResponse.message ?: "Error desconocido"
                        Result.failure(Exception(errorMsg))
                    }
                } else {
                    Result.failure(Exception("Response body es null"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception("HTTP Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }
}