package pmdm.bloggerapi.articulos.data

import pmdm.bloggerapi.articulos.data.network.ArticuloClient
import pmdm.bloggerapi.articulos.data.network.ArticuloResponse
import pmdm.bloggerapi.articulos.data.network.LineaVentaRequest
import pmdm.bloggerapi.articulos.data.network.PedidoRequest
import pmdm.bloggerapi.articulos.data.network.SeccionResponse
import retrofit2.Response
import javax.inject.Inject

class ArticuloRepository @Inject constructor(private val articulosClient: ArticuloClient) {


    /**
     * Obtiene todas las secciones disponibles
     * @return Response con lista de secciones
     */
    suspend fun getSecciones(): Response<List<SeccionResponse>> {
            return try {
                val response = articulosClient.getSecciones()
                response
            } catch (e: Exception) {
                throw e
            }
        }


    /**
     * Obtiene una sección específica por ID
     * @param seccion_id ID de la sección a buscar
     * @return Response con la sección encontrada
     */
    suspend fun getSeccion(seccion_id: Long): Response<SeccionResponse> {
        return articulosClient.getSeccion(seccion_id)
    }

    /**
     * Obtiene todos los artículos de una sección específica
     * @param seccion_id ID de la sección
     * @return Response con lista de artículos de la sección
     */
    suspend fun getArticulosPorSeccion(seccion_id: Long): Response<List<ArticuloResponse>> {
        return articulosClient.getArticulosPorSeccion(seccion_id)
    }

    /**
     * Crea un nuevo pedido
     * @param pedidoRequest Datos del pedido a crear
     * @return Response con resultado de la creación
     */
    suspend fun crearPedido(pedidoRequest: PedidoRequest): Response<pmdm.bloggerapi.clientes.data.network.CrearPedidoResponse> {
        return articulosClient.crearPedido(pedidoRequest)
    }

    /**
     * Crea una nueva línea de venta (detalle de pedido)
     * @param lineaVentaRequest Datos de la línea de venta
     * @return Response con resultado de la creación
     */
    suspend fun crearLineaVenta(lineaVentaRequest: LineaVentaRequest): Response<pmdm.bloggerapi.clientes.data.network.LineasVentaResponse> {
        return articulosClient.crearLineaVenta(lineaVentaRequest)
    }
}