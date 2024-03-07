package com.digitaul.lagunachicken.domain.dto;

import jakarta.persistence.Column;

public class ProductoDTO {

    int idProducto;
    String producto;
    Double incidenciasLitrosProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getIncidenciasLitrosProducto() {
        return incidenciasLitrosProducto;
    }

    public void setIncidenciasLitrosProducto(Double incidenciasLitrosProducto) {
        this.incidenciasLitrosProducto = incidenciasLitrosProducto;
    }
}
