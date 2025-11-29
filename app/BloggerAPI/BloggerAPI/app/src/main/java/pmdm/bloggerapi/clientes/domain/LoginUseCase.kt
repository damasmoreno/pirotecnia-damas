package pmdm.bloggerapi.clientes.domain

import pmdm.bloggerapi.clientes.data.ClienteRepository
import pmdm.bloggerapi.clientes.ui.model.Cliente
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val clienteRepository: ClienteRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Cliente> {
        return try {
            val response = clienteRepository.login(email, password)

            if (response.isSuccessful) {
                val authResponse = response.body()
                if (authResponse?.success == true && authResponse.cliente != null) {
                    Result.success(authResponse.cliente.toModel())
                } else {
                    Result.failure(Exception(authResponse?.message ?: "Credenciales incorrectas"))
                }
            } else {
                Result.failure(Exception("Error en la petición: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}