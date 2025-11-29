package pmdm.bloggerapi.articulos.domain

import pmdm.bloggerapi.articulos.data.ArticuloRepository
import pmdm.bloggerapi.articulos.data.network.PedidoRequest
import pmdm.bloggerapi.clientes.data.network.CrearPedidoResponse
import javax.inject.Inject

/**
 * Caso de uso para crear un pedido
 * Maneja la lógica de negocio para crear pedidos
 */
class CrearPedidoUseCase @Inject constructor(
    private val articuloRepository: ArticuloRepository
) {
    suspend operator fun invoke(pedidoRequest: PedidoRequest): Result<CrearPedidoResponse> {
        return try {
            val response = articuloRepository.crearPedido(pedidoRequest)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al crear pedido: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


