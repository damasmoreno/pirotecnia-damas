package com.example.apipirotecnia.service;

import com.example.apipirotecnia.model.LineaVenta;
import com.example.apipirotecnia.repository.LineaVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineaVentaService {

    @Autowired
    private LineaVentaRepository lineaVentaRepository;

    /**
     * Obtiene todas las líneas de venta de un pedido específico
     * @param pedidoId ID del pedido
     * @return Lista de líneas de venta con información de artículos cargada
     */
    public List<LineaVenta> getLineasVentaByPedidoId(Long pedidoId) {
        return lineaVentaRepository.findByPedidoIdWithArticulo(pedidoId);
    }

    /**
     * Guarda una nueva línea de venta en la base de datos
     * @param lineaVenta Línea de venta a guardar
     * @return Línea de venta guardada con ID generado
     */
    public LineaVenta guardarLineaVenta(LineaVenta lineaVenta) {
        return lineaVentaRepository.save(lineaVenta);
    }
}
