package pmdm.bloggerapi.clientes.data.network



/**
 * Respuesta de autenticación
 * @param success Indica si la autenticación fue exitosa
 * @param message Mensaje descriptivo (opcional)
 * @param cliente Datos del cliente si la autenticación fue exitosa
 */
data class AuthResponse(
    val success: Boolean,
    val message: String? = null,
    val cliente: ClienteResponse? = null
)