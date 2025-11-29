package com.example.apipirotecnia.controller;



import com.example.apipirotecnia.dto.LineaVentaDTO;
import com.example.apipirotecnia.model.LineaVenta;
import com.example.apipirotecnia.service.LineaVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventas") // Ruta base para endpoints de ventas
public class LineaVentaController {

    @Autowired
    private LineaVentaService lineaVentaService; // Inyección del servicio de líneas de venta


    /**
     * Obtiene todas las líneas de venta de un pedido específico
     * @param pedidoId ID del pedido
     * @return Lista de líneas de venta del pedido en formato DTO
     */
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<Map<String, Object>> getLineasVentaByPedidoId(@PathVariable Long pedidoId) {
        try {
            List<LineaVenta> lineasVenta = lineaVentaService.getLineasVentaByPedidoId(pedidoId);


            // Convertir entidades a DTOs para la respuesta
            List<LineaVentaDTO> lineasVentaDTO = lineasVenta.stream()
                    .map(LineaVentaDTO::new)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Líneas de venta obtenidas correctamente");
            response.put("lineasVenta", lineasVentaDTO);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al obtener las líneas de venta: " + e.getMessage());
            return ResponseEntity.status(404).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error en el servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }



    /**
     * Crea una nueva línea de venta (detalle de pedido)
     * @param lineaVenta Objeto LineaVenta con los datos a crear
     * @return Respuesta con la línea de venta creada o error
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearLineaVenta(@RequestBody LineaVenta lineaVenta) {
        try {
            // Validaciones de campos obligatorios
            if (lineaVenta.getID_Articulo() == null || lineaVenta.getID_Articulo() == 0) {
                throw new RuntimeException("ID_Articulo no puede ser null o 0");
            }
            if (lineaVenta.getID_Pedido() == null || lineaVenta.getID_Pedido() == 0) {
                throw new RuntimeException("ID_Pedido no puede ser null o 0");
            }
            if (lineaVenta.getUnidades() == null || lineaVenta.getUnidades() <= 0) {
                throw new RuntimeException("Unidades debe ser mayor a 0");
            }
            if (lineaVenta.getPrecio() == null || lineaVenta.getPrecio() <= 0) {
                System.out.println("❌ Validación fallida: Precio inválido");
                throw new RuntimeException("Precio debe ser mayor a 0");
            }
            // Guardar la línea de venta en la base de datos
            LineaVenta lineaVentaGuardada = lineaVentaService.guardarLineaVenta(lineaVenta);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Línea de venta creada correctamente");
            response.put("lineaVenta", new LineaVentaDTO(lineaVentaGuardada));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al crear la línea de venta: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


}
