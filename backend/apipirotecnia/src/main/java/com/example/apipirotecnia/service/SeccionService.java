package com.example.apipirotecnia.service;

import com.example.apipirotecnia.model.Seccion;
import com.example.apipirotecnia.repository.SeccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;





@Service
@RequiredArgsConstructor
public class SeccionService {

    @Autowired
    private SeccionRepository seccionRepo;

    /**
     * Obtiene todas las secciones ordenadas alfabéticamente por nombre
     * @return Lista de todas las secciones ordenadas
     */
    public List<Seccion> listarSecciones() {
        List<Seccion> secciones = seccionRepo.findAllByOrderByNombreAsc();

        return secciones;
    }

    /**
     * Obtiene una sección específica por ID
     * @param seccionId ID de la sección a buscar
     * @return Sección encontrada
     * @throws RuntimeException si la sección no existe
     */
    public Seccion getSeccion(Long seccionId) {
        return seccionRepo.findById(seccionId)
                .orElseThrow(() -> new RuntimeException("Sección no encontrada con id: " + seccionId));
    }
}
