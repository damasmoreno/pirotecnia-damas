package com.example.apipirotecnia.repository;

import com.example.apipirotecnia.model.LineaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineaVentaRepository extends JpaRepository<LineaVenta, Long> {

    /**
     * Busca líneas de venta por ID de pedido
     * @param pedidoId ID del pedido
     * @return Lista de líneas de venta del pedido
     */
    @Query("SELECT lv FROM LineaVenta lv WHERE lv.ID_Pedido = :pedidoId")
    List<LineaVenta> findByPedidoId(@Param("pedidoId") Long pedidoId);


    /**
     * Busca líneas de venta por ID de pedido incluyendo los artículos relacionados
     * Usa JOIN FETCH para evitar el problema N+1
     * @param pedidoId ID del pedido
     * @return Lista de líneas de venta con artículos cargados
     */
    @Query("SELECT lv FROM LineaVenta lv LEFT JOIN FETCH lv.articulo WHERE lv.ID_Pedido = :pedidoId")
    List<LineaVenta> findByPedidoIdWithArticulo(@Param("pedidoId") Long pedidoId);
}
