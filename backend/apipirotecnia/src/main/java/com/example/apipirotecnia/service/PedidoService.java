package com.example.apipirotecnia.service;

import com.example.apipirotecnia.model.Pedido;
import com.example.apipirotecnia.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    /**
     * Obtiene todos los pedidos de un cliente específico
     * @param clienteId ID del cliente
     * @return Lista de pedidos del cliente ordenados por fecha descendente
     */
    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        return pedidoRepo.findPedidosByClienteId(clienteId);
    }

    /**
     * Guarda un nuevo pedido en la base de datos
     * @param pedido Pedido a guardar
     * @return Pedido guardado con ID generado
     */
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }
}
