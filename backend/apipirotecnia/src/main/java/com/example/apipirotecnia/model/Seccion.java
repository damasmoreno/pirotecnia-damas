package com.example.apipirotecnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "seccion")
@JsonIgnoreProperties({"articulos"})
public class Seccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Seccion")
    private Long ID_Seccion;

    private String nombre;
    private String descripcion;
    private String imagenNombre;


    @OneToMany(mappedBy = "seccion", fetch = FetchType.LAZY)
   @JsonIgnore
    private List<Articulo> articulos;

    public Seccion() {

    }

    public Seccion(String nombre, String descripcion, String imagenNombre) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenNombre = imagenNombre;
    }


    public Long getID_Seccion() { return ID_Seccion; }
    public void setID_Seccion(Long ID_Seccion) { this.ID_Seccion = ID_Seccion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagenNombre() { return imagenNombre; }
    public void setImagenNombre(String imagenNombre) { this.imagenNombre = imagenNombre; }

    @JsonIgnore
    public List<Articulo> getArticulos() { return articulos; }
    public void setArticulos(List<Articulo> articulos) { this.articulos = articulos; }

    @Override
    public String toString() {
        return "Seccion{" +
                "ID_Seccion=" + ID_Seccion +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagenNombre='" + imagenNombre + '\'' +
                '}';
    }
}
