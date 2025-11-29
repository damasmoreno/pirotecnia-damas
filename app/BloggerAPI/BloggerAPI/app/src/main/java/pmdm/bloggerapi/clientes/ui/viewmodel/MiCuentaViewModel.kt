package pmdm.bloggerapi.clientes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pmdm.bloggerapi.clientes.domain.GetPedidosUseCase
import pmdm.bloggerapi.clientes.ui.model.Pedido
import javax.inject.Inject

@HiltViewModel
class MiCuentaViewModel @Inject constructor(
    private val getPedidosUseCase: GetPedidosUseCase
) : ViewModel() {

    // Estados para mensajes y pedidos
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos: StateFlow<List<Pedido>> = _pedidos.asStateFlow()

    private val _isLoadingPedidos = MutableStateFlow(false)
    val isLoadingPedidos: StateFlow<Boolean> = _isLoadingPedidos.asStateFlow()

    /**
     * Carga los pedidos de un cliente
     * @param clienteId ID del cliente
     */
    fun loadPedidos(clienteId: Long) {
        viewModelScope.launch {
            _isLoadingPedidos.value = true
            try {

                val result = getPedidosUseCase(clienteId)

                if (result.isSuccess) {
                    val pedidosList = result.getOrNull() ?: emptyList()

                    _pedidos.value = pedidosList
                } else {
                    val errorMsg = "Error al cargar pedidos: ${result.exceptionOrNull()?.message}"

                    _message.value = errorMsg
                }
            } catch (e: Exception) {
                val errorMsg = "Error de conexión al cargar pedidos: ${e.message}"

                _message.value = errorMsg
            } finally {
                _isLoadingPedidos.value = false
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}


