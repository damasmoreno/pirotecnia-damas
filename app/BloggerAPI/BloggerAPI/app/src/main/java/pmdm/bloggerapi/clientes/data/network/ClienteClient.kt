package pmdm.bloggerapi.clientes.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ClienteClient {

        /**
         * Endpoint para login de clientes
         * POST /clientes/login
         * @param authRequest Credenciales de autenticación
         * @return Response con resultado de autenticación
         */
        @POST("/clientes/login")
        suspend fun login(@Body authRequest: AuthRequest): Response<AuthResponse>






}
