package com.example.apipirotecnia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) para LineaVenta
 * Se utiliza para transferir datos entre capas sin exponer la entidad completa
 */

public class LineaVentaDTO {

    @JsonProperty("ID_Venta")
    private Long idVenta;

    @JsonProperty("ID_Articulo")
    private Long idArticulo;

    @JsonProperty("Unidades")
    private Integer unidades;

    @JsonProperty("Precio")
    private Double precio;

    @JsonProperty("Total")
    private Double total;

    @JsonProperty("ID_Pedido")
    private Long idPedido;

    @JsonProperty("descripcionArticulo")
    private String descripcionArticulo;


    /**
     * Constructor que convierte una entidad LineaVenta a DTO
     * @param lineaVenta Entidad LineaVenta a convertir
     */
    public LineaVentaDTO(com.example.apipirotecnia.model.LineaVenta lineaVenta) {
        this.idVenta = lineaVenta.getID_Venta();
        this.idArticulo = lineaVenta.getID_Articulo();
        this.unidades = lineaVenta.getUnidades();
        this.precio = lineaVenta.getPrecio();
        this.total = lineaVenta.getTotal();
        this.idPedido = lineaVenta.getID_Pedido();
        this.descripcionArticulo = lineaVenta.getDescripcionArticulo();
    }

    // Getters y Setters
    public Long getIdVenta() { return idVenta; }
    public void setIdVenta(Long idVenta) { this.idVenta = idVenta; }

    public Long getIdArticulo() { return idArticulo; }
    public void setIdArticulo(Long idArticulo) { this.idArticulo = idArticulo; }

    public Integer getUnidades() { return unidades; }
    public void setUnidades(Integer unidades) { this.unidades = unidades; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Long getIdPedido() { return idPedido; }
    public void setIdPedido(Long idPedido) { this.idPedido = idPedido; }

    public String getDescripcionArticulo() { return descripcionArticulo; }
    public void setDescripcionArticulo(String descripcionArticulo) { this.descripcionArticulo = descripcionArticulo; }
}
