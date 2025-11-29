package pmdm.bloggerapi.articulos.ui.viewmodel



import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pmdm.bloggerapi.articulos.domain.CrearLineaVentaUseCase
import pmdm.bloggerapi.articulos.domain.CrearPedidoUseCase
import javax.inject.Inject

@HiltViewModel
class CarritoHelperViewModel @Inject constructor(
    val crearPedidoUseCase: CrearPedidoUseCase,
    val crearLineaVentaUseCase: CrearLineaVentaUseCase
) : ViewModel()