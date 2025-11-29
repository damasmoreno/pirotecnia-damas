package com.example.apipirotecnia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Pedido;

    @JsonProperty("ID_Cliente")
    @Column(name = "ID_Cliente")
    private Long ID_Cliente;


    @Column(name = "Fecha_pedido")
    private LocalDateTime Fecha_pedido;

    @JsonProperty("Total")
    @Column(name = "total")
    private Double Total;

    @JsonProperty("Entregado")
    @Column(name = "Entregado")
    private Boolean Entregado;



    // Getters y Setters
    public Long getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(Long ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public Long getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(Long ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public LocalDateTime getFecha_pedido() {
        return Fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        Fecha_pedido = fecha_pedido;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public Boolean getEntregado() {
        return Entregado;
    }

    public void setEntregado(Boolean entregado) {
        Entregado = entregado;
    }


}