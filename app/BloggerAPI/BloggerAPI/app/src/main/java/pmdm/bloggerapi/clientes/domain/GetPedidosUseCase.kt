package pmdm.bloggerapi.clientes.domain

import pmdm.bloggerapi.clientes.data.PedidoRepository
import pmdm.bloggerapi.clientes.ui.model.Pedido
import javax.inject.Inject

class GetPedidosUseCase @Inject constructor(
    private val pedidoRepository: PedidoRepository
) {
    suspend operator fun invoke(clienteId: Long): Result<List<Pedido>> {
        return try {
            val result = pedidoRepository.getPedidosByClienteId(clienteId)

            if (result.isSuccess) {
                var pedidos = result.getOrNull() ?: emptyList()


                pedidos = pedidos.sortedByDescending { it.id }

                Result.success(pedidos)
            } else {
                val error = result.exceptionOrNull() ?: Exception("Error al cargar pedidos")
                Result.failure(error)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


