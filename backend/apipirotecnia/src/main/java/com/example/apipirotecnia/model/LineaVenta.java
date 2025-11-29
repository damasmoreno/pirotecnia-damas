package com.example.apipirotecnia.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "detalle_pedido")
public class LineaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Venta")
    private Long ID_Venta;

    @Column(name = "ID_Articulo", nullable = false)
    @JsonProperty("ID_Articulo")
    private Long ID_Articulo;

    @Column(name = "Unidades", nullable = false)
    @JsonProperty("Unidades")
    private Integer Unidades;

    @Column(name = "Precio", nullable = false)
    @JsonProperty("Precio")
    private Double Precio;

    @Column(name = "Total", nullable = false)
    @JsonProperty("Total")
    private Double Total;

    @Column(name = "ID_Pedido", nullable = false)
    @JsonProperty("ID_Pedido")
    private Long ID_Pedido;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Articulo", insertable = false, updatable = false)
    private Articulo articulo;


    public Long getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(Long ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    public Long getID_Articulo() {
        return ID_Articulo;
    }

    public void setID_Articulo(Long ID_Articulo) {
        this.ID_Articulo = ID_Articulo;
    }

    public Integer getUnidades() {
        return Unidades;
    }

    public void setUnidades(Integer unidades) {
        Unidades = unidades;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public Long getID_Pedido() {
        return ID_Pedido;
    }

    public void setID_Pedido(Long ID_Pedido) {
        this.ID_Pedido = ID_Pedido;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }


    public String getDescripcionArticulo() {
        return articulo != null ? articulo.getDescripcion() : "Artículo " + ID_Articulo;
    }
}