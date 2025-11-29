package pmdm.bloggerapi.articulos.domain

import pmdm.bloggerapi.articulos.data.ArticuloRepository
import pmdm.bloggerapi.articulos.ui.model.Seccion
import javax.inject.Inject

/**
 * Caso de uso para obtener todas las secciones
 * Maneja la lógica de negocio para recuperar secciones
 */
class GetSeccionesUseCase @Inject constructor(private val articuloRepository: ArticuloRepository) {
    suspend operator fun invoke(): Result<List<Seccion>> {
        return try {
            val response = articuloRepository.getSecciones()


            if (response.isSuccessful) {
                val secciones = response.body()?.map { it.toModel() } ?: emptyList()
                Result.success(secciones)
            } else {
                val errorMsg = "Error en la petición: ${response.code()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}