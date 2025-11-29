package pmdm.bloggerapi.clientes.data

import pmdm.bloggerapi.clientes.data.network.AuthRequest
import pmdm.bloggerapi.clientes.data.network.AuthResponse
import pmdm.bloggerapi.clientes.data.network.ClienteClient
import retrofit2.Response
import javax.inject.Inject

class ClienteRepository @Inject constructor(private val clientesClient: ClienteClient) {


    /**
     * Realiza el login del cliente
     * @param email Correo electrónico del cliente
     * @param password Contraseña del cliente
     * @return Response con el resultado de la autenticación
     */
    suspend fun login(email: String, password: String): Response<AuthResponse> {
        val authRequest = AuthRequest(correoelectronico = email, password = password)
        return clientesClient.login(authRequest)
    }



}

