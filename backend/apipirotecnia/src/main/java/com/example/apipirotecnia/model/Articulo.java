package com.example.apipirotecnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Articulo;

    private String descripcion;
    private double precio;
    private String fechaAlta;
    private String imagenNombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Seccion")
   @JsonIgnore
    private Seccion seccion;


    public Long getID_Articulo() {
        return ID_Articulo;
    }
    public void setID_Articulo(Long ID_Articulo) {
        this.ID_Articulo = ID_Articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getFechaAlta() { return fechaAlta; }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getImagenNombre() {
        return imagenNombre;
    }
    public void setImagenNombre(String imagenNombre) {
        this.imagenNombre = imagenNombre;
    }
    @JsonIgnore
    public Seccion getSeccion() { return seccion; }
    public void setSeccion(Seccion seccion) { this.seccion = seccion; }
}


