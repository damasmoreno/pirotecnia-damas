package pmdm.bloggerapi.articulos.domain

import pmdm.bloggerapi.articulos.data.ArticuloRepository
import pmdm.bloggerapi.articulos.ui.model.Articulo
import javax.inject.Inject

/**
 * Caso de uso para obtener artículos por sección
 * Maneja la lógica de negocio para recuperar artículos filtrados
 */
class GetArticulosPorSeccionUseCase @Inject constructor(private val articuloRepository: ArticuloRepository) {
    suspend operator fun invoke(seccionId: Long): Result<List<Articulo>> {
        return try {
            val response = articuloRepository.getArticulosPorSeccion(seccionId)
            if (response.isSuccessful) {
                Result.success(response.body()!!.map { it.toModel() })
            } else {
                Result.failure(Exception("Error en la petición: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}