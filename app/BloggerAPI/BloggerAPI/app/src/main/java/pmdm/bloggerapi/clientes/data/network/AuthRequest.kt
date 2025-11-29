package pmdm.bloggerapi.clientes.data.network

import kotlinx.serialization.Serializable

@Serializable
/**
 * Request para autenticación de cliente
 * @param correoelectronico Correo electrónico del cliente
 * @param password Contraseña del cliente
 */
data class AuthRequest(
    val correoelectronico: String,
    val password: String
)