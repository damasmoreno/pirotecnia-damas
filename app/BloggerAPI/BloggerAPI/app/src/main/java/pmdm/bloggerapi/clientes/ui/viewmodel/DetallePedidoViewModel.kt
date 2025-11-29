package pmdm.bloggerapi.clientes.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pmdm.bloggerapi.clientes.domain.GetLineasVentaUseCase
import pmdm.bloggerapi.clientes.ui.model.LineaVenta
import javax.inject.Inject


@HiltViewModel
class DetallePedidoViewModel @Inject constructor(
    private val getLineasVentaUseCase: GetLineasVentaUseCase
) : ViewModel() {

    // Estados para las líneas de venta
    private val _lineasVenta = MutableStateFlow<List<LineaVenta>>(emptyList())
    val lineasVenta: StateFlow<List<LineaVenta>> = _lineasVenta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    /**
     * Carga las líneas de venta de un pedido
     * @param pedidoId ID del pedido
     */
    fun loadLineasVenta(pedidoId: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _message.value = null

            try {
                val result = getLineasVentaUseCase(pedidoId)

                if (result.isSuccess) {
                    _lineasVenta.value = result.getOrNull() ?: emptyList()

                } else {
                    _message.value = result.exceptionOrNull()?.message ?: "Error al cargar los artículos"
                }
            } catch (e: Exception) {
                _message.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}