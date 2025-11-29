package pmdm.bloggerapi.clientes.domain

import pmdm.bloggerapi.clientes.data.LineaVentaRepository
import pmdm.bloggerapi.clientes.ui.model.LineaVenta
import javax.inject.Inject

class GetLineasVentaUseCase @Inject constructor(
    private val lineaVentaRepository: LineaVentaRepository
) {
    suspend operator fun invoke(pedidoId: Long): Result<List<LineaVenta>> {
        return try {
            val result = lineaVentaRepository.getLineasVentaByPedidoId(pedidoId)

            if (result.isSuccess) {
                val lineasVenta = result.getOrNull() ?: emptyList()
                Result.success(lineasVenta)
            } else {
                val error = result.exceptionOrNull() ?: Exception("Error al cargar líneas de venta")
                Result.failure(error)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}