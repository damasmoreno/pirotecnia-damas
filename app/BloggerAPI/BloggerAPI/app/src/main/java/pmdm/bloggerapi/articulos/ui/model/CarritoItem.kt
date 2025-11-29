package pmdm.bloggerapi.articulos.ui.model



data class CarritoItem(
    val articulo: Articulo,
    var cantidad: Int = 1
) {
    /**
     * Calcula el total para este item (precio * cantidad)
     * @return Total calculado como Double
     */
    fun getTotal(): Double {
        val precio = articulo.precio.toDoubleOrNull() ?: 0.0
        val total = precio * cantidad
        println("💰 Calculando total para ${articulo.descripcion}: $precio x $cantidad = $total")
        return total
    }
}