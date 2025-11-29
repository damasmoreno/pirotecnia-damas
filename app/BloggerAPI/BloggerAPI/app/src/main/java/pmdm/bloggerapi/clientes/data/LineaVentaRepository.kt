package pmdm.bloggerapi.clientes.data

import pmdm.bloggerapi.clientes.data.network.LineaVentaClient
import pmdm.bloggerapi.clientes.ui.model.LineaVenta
import javax.inject.Inject

class LineaVentaRepository @Inject constructor(
    private val lineaVentaClient: LineaVentaClient
) {
    /**
     * Obtiene las líneas de venta de un pedido específico
     * @param pedidoId ID del pedido
     * @return Result con lista de líneas de venta o error
     */
    suspend fun getLineasVentaByPedidoId(pedidoId: Long): Result<List<LineaVenta>> {
        return try {
            val response = lineaVentaClient.getLineasVentaByPedidoId(pedidoId)



            if (response.isSuccessful) {
                val lineasVentaResponse = response.body()


                if (lineasVentaResponse?.success == true) {
                    val lineasVenta = lineasVentaResponse.lineasVenta.map { it.toModel() }
                    Result.success(lineasVenta)
                } else {
                    val errorMsg = lineasVentaResponse?.message ?: "Error al obtener líneas de venta"
                    Result.failure(Exception(errorMsg))
                }
            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }
}