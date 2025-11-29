package com.example.apipirotecnia.repository;

import com.example.apipirotecnia.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca todos los pedidos de un cliente específico ordenados por fecha descendente
     * @param clienteId ID del cliente
     * @return Lista de pedidos del cliente, más recientes primero
     */
    @Query("SELECT p FROM Pedido p WHERE p.ID_Cliente = :clienteId ORDER BY p.Fecha_pedido DESC")
    List<Pedido> findPedidosByClienteId(@Param("clienteId") Long clienteId);
}
