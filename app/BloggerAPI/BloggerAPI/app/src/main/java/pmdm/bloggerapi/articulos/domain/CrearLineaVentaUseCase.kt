package pmdm.bloggerapi.articulos.domain

import pmdm.bloggerapi.articulos.data.ArticuloRepository
import pmdm.bloggerapi.articulos.data.network.LineaVentaRequest
import pmdm.bloggerapi.clientes.data.network.LineasVentaResponse
import javax.inject.Inject

/**
 * Caso de uso para crear una línea de venta
 * Maneja la lógica de negocio para crear detalles de pedido
 */
class CrearLineaVentaUseCase @Inject constructor(
    private val articuloRepository: ArticuloRepository
) {
    suspend operator fun invoke(lineaVentaRequest: LineaVentaRequest): Result<LineasVentaResponse> {
        return try {
            val response = articuloRepository.crearLineaVenta(lineaVentaRequest)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error al crear línea de venta: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

