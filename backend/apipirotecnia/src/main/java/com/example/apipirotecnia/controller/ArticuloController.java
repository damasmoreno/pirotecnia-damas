package com.example.apipirotecnia.controller;


import com.example.apipirotecnia.model.Articulo;
import com.example.apipirotecnia.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articulos")  // Ruta base para endpoints de artículos
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;  // Inyección del servicio de artículos

    /**
     * Obtiene todos los artículos de una sección específica
     * @param seccionId ID de la sección a filtrar
     * @return Lista de artículos de la sección o 404 si no existe
     */

    @GetMapping("/seccion/{seccionId}")
    public ResponseEntity<List<Articulo>> getArticulosPorSeccion(@PathVariable Long seccionId) {
        try {
            return ResponseEntity.ok(articuloService.getArticulosPorSeccion(seccionId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
