package com.example.apipirotecnia.controller;

import com.example.apipirotecnia.model.Cliente;
import com.example.apipirotecnia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")  // Ruta base para endpoints de clientes
public class ClienteController {

    @Autowired
    private ClienteService clienteService;  // Inyección del servicio de clientes


    /**
     * Endpoint para autenticación de clientes
     * @param credentials Map con email y password del cliente
     * @return Respuesta con estado de login y datos del cliente si es exitoso
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("correoelectronico");
            String password = credentials.get("password");

            // Buscar cliente por email y password
            Cliente cliente = clienteService.findByEmailAndPassword(email, password);

            // Construir objeto cliente para la respuesta
            Map<String, Object> response = new HashMap<>();
            if (cliente != null) {
                response.put("success", true);
                response.put("message", "Login exitoso");


                Map<String, Object> clienteMap = new HashMap<>();
                clienteMap.put("id", cliente.getid());
                clienteMap.put("Nombre", cliente.getNombre());
                clienteMap.put("nacionalidad", cliente.getNacionalidad());
                clienteMap.put("nif", cliente.getNif());
                clienteMap.put("fechaNacimiento", cliente.getFechaNacimiento());
                clienteMap.put("correo_electronico", cliente.getCorreo_electronico());
                clienteMap.put("password", cliente.getPassword());

                response.put("cliente", clienteMap);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Credenciales incorrectas");
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error en el servidor: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}