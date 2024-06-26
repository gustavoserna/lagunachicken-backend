package com.digitaul.lagunachicken.domain.dto;

import jakarta.validation.constraints.NotNull;

public class VehiculoConsumoDTO {

    int idVehiculoConsumo;
    String despacho;
    @NotNull
    int vehiculoIdVehiculo;
    @NotNull
    int estacionIdEstacion;
    @NotNull
    int productoIdProducto;
    VehiculoDTO vehiculoDTO;
    EstacionDTO estacionDTO;
    ProductoDTO productoDTO;
    @NotNull
    String odometro;
    @NotNull
    String recorrido;
    @NotNull
    String rendimiento;
    @NotNull
    String cantidad;
    @NotNull
    String precio;
    @NotNull
    String monto;
    @NotNull
    String horaConsumo;
    @NotNull
    String fechaConsumo;

    public int getIdVehiculoConsumo() {
        return idVehiculoConsumo;
    }

    public void setIdVehiculoConsumo(int idVehiculoConsumo) {
        this.idVehiculoConsumo = idVehiculoConsumo;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public int getVehiculoIdVehiculo() {
        return vehiculoIdVehiculo;
    }

    public void setVehiculoIdVehiculo(int vehiculoIdVehiculo) {
        this.vehiculoIdVehiculo = vehiculoIdVehiculo;
    }

    public int getEstacionIdEstacion() {
        return estacionIdEstacion;
    }

    public void setEstacionIdEstacion(int estacionIdEstacion) {
        this.estacionIdEstacion = estacionIdEstacion;
    }

    public int getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    public VehiculoDTO getVehiculoDTO() {
        return vehiculoDTO;
    }

    public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
        this.vehiculoDTO = vehiculoDTO;
    }

    public EstacionDTO getEstacionDTO() {
        return estacionDTO;
    }

    public void setEstacionDTO(EstacionDTO estacionDTO) {
        this.estacionDTO = estacionDTO;
    }

    public ProductoDTO getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(ProductoDTO productoDTO) {
        this.productoDTO = productoDTO;
    }

    public String getOdometro() {
        return odometro;
    }

    public void setOdometro(String odometro) {
        this.odometro = odometro;
    }

    public String getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getHoraConsumo() {
        return horaConsumo;
    }

    public void setHoraConsumo(String horaConsumo) {
        this.horaConsumo = horaConsumo;
    }

    public String getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(String fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }
}
