package pmdm.bloggerapi.core.managers



import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import pmdm.bloggerapi.articulos.data.network.LineaVentaRequest
import pmdm.bloggerapi.articulos.data.network.PedidoRequest
import pmdm.bloggerapi.articulos.domain.CrearLineaVentaUseCase
import pmdm.bloggerapi.articulos.domain.CrearPedidoUseCase
import pmdm.bloggerapi.articulos.ui.model.Articulo
import pmdm.bloggerapi.articulos.ui.model.CarritoItem

@Stable
object CarritoManager {
    private val _items: SnapshotStateList<CarritoItem> = mutableStateListOf()
    private val _total = mutableStateOf(0.0)
    private val _cantidad = mutableStateOf(0)
    private val _isLoading = mutableStateOf(false)
    private val _message = mutableStateOf<String?>(null)

    // States públicos
    val items: List<CarritoItem> get() = _items.toList()
    val total: Double get() = _total.value
    val cantidad: Int get() = _cantidad.value
    val isLoading: Boolean get() = _isLoading.value
    val message: String? get() = _message.value

    // Funciones para obtener states como StateFlow (para Compose)
    fun getTotalState() = _total
    fun getCantidadState() = _cantidad
    fun getItemsState() = _items
    fun getIsLoadingState() = _isLoading
    fun getMessageState() = _message

    /**
     * Agrega un artículo al carrito o incrementa su cantidad si ya existe
     * @param articulo Artículo a agregar
     */
    fun agregarAlCarrito(articulo: Articulo) {
        val itemExistente = _items.find { it.articulo.ID_Articulo == articulo.ID_Articulo }

        if (itemExistente != null) {
            val index = _items.indexOf(itemExistente)
            _items[index] = itemExistente.copy(cantidad = itemExistente.cantidad + 1)
        } else {
            _items.add(CarritoItem(articulo, 1))
        }
        calcularTotales()
    }

    /**
     * Elimina un artículo del carrito
     * @param articulo Artículo a eliminar
     */
    fun eliminarDelCarrito(articulo: Articulo) {
        _items.removeAll { it.articulo.ID_Articulo == articulo.ID_Articulo }
        calcularTotales()
    }

    /**
     * Actualiza la cantidad de un artículo en el carrito
     * @param articulo Artículo a actualizar
     * @param nuevaCantidad Nueva cantidad (si es 0 o menor, se elimina)
     */
    fun actualizarCantidad(articulo: Articulo, nuevaCantidad: Int) {
        if (nuevaCantidad <= 0) {
            eliminarDelCarrito(articulo)
            return
        }

        val item = _items.find { it.articulo.ID_Articulo == articulo.ID_Articulo }
        item?.let {
            val index = _items.indexOf(item)
            _items[index] = it.copy(cantidad = nuevaCantidad)
            calcularTotales()
        }
    }

    /**
     * Vacía completamente el carrito
     */
    fun limpiarCarrito() {
        _items.clear()
        calcularTotales()
    }


    /**
     * Finaliza el pedido creando el pedido y las líneas de venta en la API
     * @param clienteId ID del cliente que realiza el pedido
     * @param crearPedidoUseCase Caso de uso para crear pedidos
     * @param crearLineaVentaUseCase Caso de uso para crear líneas de venta
     * @return true si el pedido se creó exitosamente, false en caso contrario
     */
    suspend fun finalizarPedido(
        clienteId: Long,
        crearPedidoUseCase: CrearPedidoUseCase,
        crearLineaVentaUseCase: CrearLineaVentaUseCase
    ): Boolean {
        if (_items.isEmpty()) {
            _message.value = "El carrito está vacío"
            return false
        }

        _isLoading.value = true
        _message.value = null

        return try {
            val pedidoRequest = PedidoRequest(
                ID_Cliente = clienteId,
                Total = _total.value,
                Entregado = false
            )

            // Crear el pedido en la API
            val pedidoResult = crearPedidoUseCase(pedidoRequest)

            if (pedidoResult.isSuccess) {
                val pedidoResponse = pedidoResult.getOrNull()

                if (pedidoResponse?.success == true && pedidoResponse.pedido != null) {
                    val pedidoId = pedidoResponse.pedido.idPedido ?: 0L

                    if (pedidoId != 0L) {
                        // Crear las líneas de venta para cada item del carrito
                        var todasLineasCreadas = true
                        var lineasCreadas = 0
                        var errores = ""

                        for (item in _items) {
                            val precio = item.articulo.precio.toDoubleOrNull() ?: 0.0
                            val totalLinea = item.getTotal()

                            val lineaVentaRequest = LineaVentaRequest(
                                ID_Articulo = item.articulo.ID_Articulo ?: 0L,
                                Unidades = item.cantidad,
                                Precio = precio,
                                Total = totalLinea,
                                ID_Pedido = pedidoId
                            )

                            val lineaResult = crearLineaVentaUseCase(lineaVentaRequest)
                            if (lineaResult.isSuccess) {
                                lineasCreadas++
                            } else {
                                todasLineasCreadas = false
                                val errorMsg = lineaResult.exceptionOrNull()?.message ?: "Error desconocido"
                                errores += "❌ Error con '${item.articulo.descripcion}': $errorMsg\n"
                            }
                        }

                        // Manejar resultado de las líneas de venta
                        if (todasLineasCreadas) {
                            _message.value = "✅ Pedido creado exitosamente"
                            limpiarCarrito() // Vaciar carrito si todo salió bien
                            true
                        } else {
                            _message.value = "⚠️ Pedido creado pero con errores en líneas:\n$errores"
                            false
                        }
                    } else {
                        _message.value = "❌ Error: ID del pedido es 0"
                        false
                    }
                } else {
                    val errorMsg = pedidoResponse?.message ?: "Respuesta inválida del servidor"
                    _message.value = "❌ Error al crear pedido: $errorMsg"
                    false
                }
            } else {
                val errorMsg = pedidoResult.exceptionOrNull()?.message ?: "Error desconocido"
                _message.value = "❌ Error al crear pedido: $errorMsg"
                false
            }
        } catch (e: Exception) {
            _message.value = "❌ Error: ${e.localizedMessage}"
            false
        } finally {
            _isLoading.value = false
        }
    }

    fun clearMessage() {
        _message.value = null
    }

    /**
     * Calcula y actualiza los totales del carrito
     */
    private fun calcularTotales() {
        val nuevoTotal = _items.sumOf { it.getTotal() }
        val nuevaCantidad = _items.sumOf { it.cantidad }

        _total.value = nuevoTotal
        _cantidad.value = nuevaCantidad
    }
}

