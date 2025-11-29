package com.example.apipirotecnia.service;

import com.example.apipirotecnia.model.Articulo;
import com.example.apipirotecnia.repository.ArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepo;


    /**
     * Obtiene todos los artículos de una sección específica
     * @param seccionId ID de la sección
     * @return Lista de artículos de la sección
     * @throws RuntimeException si no se encuentran artículos
     */
    public List<Articulo> getArticulosPorSeccion(Long seccionId) {
        return articuloRepo.findArticulosBySeccionId(seccionId);
    }

}
