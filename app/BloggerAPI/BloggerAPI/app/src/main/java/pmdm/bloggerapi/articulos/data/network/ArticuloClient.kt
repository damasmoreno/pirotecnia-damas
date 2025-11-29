package pmdm.bloggerapi.articulos.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticuloClient {

    /**
     * Obtiene todas las secciones
     * Endpoint: GET /secciones
     */
    @GET("/secciones")
    suspend fun getSecciones(): Response<List<SeccionResponse>>

    /**
     * Obtiene una sección específica por ID
     * Endpoint: GET /secciones/{id}
     * @param seccionId ID de la sección
     */
    @GET("/secciones/{id}")
    suspend fun getSeccion(@Path("id") seccionId: Long): Response<SeccionResponse>

    /**
     * Obtiene artículos por ID de sección
     * Endpoint: GET /articulos/seccion/{seccionId}
     * @param seccionId ID de la sección
     */
    @GET("/articulos/seccion/{seccionId}")
    suspend fun getArticulosPorSeccion(@Path("seccionId") seccionId: Long): Response<List<ArticuloResponse>>

    /**
     * Crea un nuevo pedido
     * Endpoint: POST /pedidos
     * @param pedido Datos del pedido a crear
     */
    @POST("/pedidos")
    suspend fun crearPedido(@Body pedido: PedidoRequest): Response<pmdm.bloggerapi.clientes.data.network.CrearPedidoResponse>

    /**
     * Crea una nueva línea de venta
     * Endpoint: POST /ventas
     * @param lineaVenta Datos de la línea de venta
     */
    @POST("/ventas")
    suspend fun crearLineaVenta(@Body lineaVenta: LineaVentaRequest): Response<pmdm.bloggerapi.clientes.data.network.LineasVentaResponse>
}