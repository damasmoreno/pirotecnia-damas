package com.example.apipirotecnia.controller;

import com.example.apipirotecnia.model.Seccion;
import com.example.apipirotecnia.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/secciones") // Ruta base para endpoints de secciones
public class SeccionController {

    @Autowired
    private SeccionService seccionService; // Inyección del servicio de secciones

    /**
     * Obtiene todas las secciones ordenadas
     * @return Lista de todas las secciones
     */
    @GetMapping
    public ResponseEntity<List<Seccion>> listarSecciones() {
        try {
            List<Seccion> secciones = seccionService.listarSecciones();
            return ResponseEntity.ok(secciones);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Obtiene una sección específica por ID
     * @param id ID de la sección a buscar
     * @return Sección encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Seccion> getSeccion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(seccionService.getSeccion(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

