package pmdm.bloggerapi.navigation

import kotlinx.serialization.Serializable

// Pantalla principal
@Serializable
object MainScreen

// Pantalla para ver artículos (secciones)
@Serializable
object VerArticulos

// Pantalla de mi cuenta
@Serializable
object MiCuenta

// Pantalla del carrito con parámetro clienteId
@Serializable
data class Carrito(val clienteId: Long)

// Pantalla de detalle de pedido con parámetro pedidoId
@Serializable
data class DetallePedido(val pedidoId: Long)
// Pantalla de artículos por sección con parámetros
@Serializable
data class ArticulosPorSeccion(
    val seccionId: Long,
    val seccionNombre: String
)