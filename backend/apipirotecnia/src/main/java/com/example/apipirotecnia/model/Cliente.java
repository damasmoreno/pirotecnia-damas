package com.example.apipirotecnia.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nombre")
    private String nombre;
    private String nacionalidad;
    private String nif;
    private String fechaNacimiento;
    private String correo_electronico;
    private String password;


    public Long getid() {
        return id;
    }
    public void setid(Long id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }
    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo_electronico() { return correo_electronico; }
    public void setCorreo_electronico(String correo_electronico) {this.correo_electronico = correo_electronico; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

