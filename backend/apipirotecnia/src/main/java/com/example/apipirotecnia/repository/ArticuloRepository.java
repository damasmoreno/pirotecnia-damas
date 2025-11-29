package com.example.apipirotecnia.repository;


import com.example.apipirotecnia.model.Articulo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    /**
     * Busca artículos por ID de sección
     * @param seccionId ID de la sección
     * @return Lista de artículos que pertenecen a la sección especificada
     */
    @Query("SELECT a FROM Articulo a WHERE a.seccion.ID_Seccion = :seccionId")
    List<Articulo> findArticulosBySeccionId(@Param("seccionId") Long seccionId);}