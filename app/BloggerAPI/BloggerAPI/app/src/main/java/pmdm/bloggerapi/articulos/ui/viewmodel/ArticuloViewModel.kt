package pmdm.bloggerapi.articulos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pmdm.bloggerapi.articulos.domain.GetArticulosPorSeccionUseCase
import pmdm.bloggerapi.articulos.domain.GetSeccionesUseCase
import pmdm.bloggerapi.articulos.ui.model.Articulo
import pmdm.bloggerapi.articulos.ui.model.Seccion
import javax.inject.Inject

@HiltViewModel // Hilt para inyección de dependencias
class ArticuloViewModel @Inject constructor(
    private val getSeccionesUseCase: GetSeccionesUseCase,
    private val getArticulosPorSeccionUseCase: GetArticulosPorSeccionUseCase
) : ViewModel() {

    // Estado para la lista de secciones
    private val _secciones = MutableStateFlow<List<Seccion>>(emptyList())
    val secciones: StateFlow<List<Seccion>> = _secciones

    // Estado para la lista de artículos
    private val _articulos = MutableStateFlow<List<Articulo>>(emptyList())
    val articulos: StateFlow<List<Articulo>> = _articulos

    // Estado de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Estado para mensajes
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    // Estado para la sección actual
    private val _currentSeccion = MutableStateFlow<Seccion?>(null)


    // Inicializar cargando secciones
    init {
        getSecciones()
    }

    /**
     * Obtiene todas las secciones
     */
    fun getSecciones() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = getSeccionesUseCase()


                result.fold(
                    onSuccess = {
                        _secciones.value = it
                        _isLoading.value = false
                    },
                    onFailure = {
                        _message.value = it.message
                        _isLoading.value = false
                    }
                )
            } catch (e: Exception) {
                _message.value = e.message
                _isLoading.value = false
            }
        }
    }

    /**
     * Obtiene artículos por ID de sección
     * @param seccionId ID de la sección
     */
    fun getArticulosPorSeccion(seccionId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = getArticulosPorSeccionUseCase(seccionId)


                result.fold(
                    onSuccess = {
                        _articulos.value = it
                        _currentSeccion.value = _secciones.value.find { seccion ->
                            seccion.ID_Seccion == seccionId
                        }
                        _isLoading.value = false
                    },
                    onFailure = {
                        _message.value = it.message
                        _isLoading.value = false
                    }
                )
            } catch (e: Exception) {
                _message.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun clearMessage() {
        _message.value = null
    }
}