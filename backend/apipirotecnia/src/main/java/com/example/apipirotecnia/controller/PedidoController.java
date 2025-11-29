package com.example.apipirotecnia.controller;

import com.example.apipirotecnia.model.Pedido;
import com.example.apipirotecnia.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos") // Ruta base para endpoints de pedidos
public class PedidoController {

    @Autowired
    private PedidoService pedidoService; // Inyección del servicio de pedidos


    /**
     * Obtiene todos los pedidos de un cliente específico
     * @param clienteId ID del cliente
     * @return Lista de pedidos del cliente
     */
    @GetMapping("/mis-pedidos/{clienteId}")
    public ResponseEntity<Map<String, Object>> getMisPedidos(@PathVariable Long clienteId) {
        try {
            List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(clienteId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Pedidos obtenidos correctamente");
            response.put("pedidos", pedidos);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener los pedidos: " + e.getMessage());
            return ResponseEntity.status(404).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error en el servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Crea un nuevo pedido
     * @param pedido Objeto Pedido con los datos a crear
     * @return Respuesta con el pedido creado o error
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearPedido(@RequestBody Pedido pedido) {
        try {
            // Validaciones de campos obligatorios
            if (pedido.getID_Cliente() == null) {
                throw new RuntimeException("ID_Cliente no puede ser null");
            }
            if (pedido.getTotal() == null || pedido.getTotal() <= 0) {
                throw new RuntimeException("Total debe ser mayor a 0");
            }

            // Establecer valores automáticos
            pedido.setFecha_pedido(LocalDateTime.now());
            pedido.setEntregado(false);
            // Guardar el pedido en la base de datos
            Pedido pedidoGuardado = pedidoService.guardarPedido(pedido);


            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Pedido creado correctamente");
            response.put("pedido", pedidoGuardado);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al crear el pedido: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}



