package com.example.apipirotecnia.repository;

import com.example.apipirotecnia.model.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeccionRepository extends JpaRepository<Seccion, Long> {
    /**
     * Encuentra todas las secciones ordenadas por nombre ascendente
     * @return Lista de secciones ordenadas alfabéticamente
     */
    List<Seccion> findAllByOrderByNombreAsc();
}
