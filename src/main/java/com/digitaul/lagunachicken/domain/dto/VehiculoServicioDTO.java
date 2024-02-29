package com.digitaul.lagunachicken.domain.dto;

public class VehiculoServicioDTO {
    int idVehiculoServicio;
    int vehiculoIdvehiculo;
    int servicioIdServicio;
    int proveedorIdProveedor;
    VehiculoDTO vehiculoDTO;
    ServicioDTO servicioDTO;
    ProveedorDTO proveedorDTO;
    String kilometraje;
    String folioFactura;
    String costo;
    String fechaServicio;
    String descripcion;

    public int getIdVehiculoServicio() {
        return idVehiculoServicio;
    }

    public void setIdVehiculoServicio(int idVehiculoServicio) {
        this.idVehiculoServicio = idVehiculoServicio;
    }

    public int getVehiculoIdvehiculo() {
        return vehiculoIdvehiculo;
    }

    public void setVehiculoIdvehiculo(int vehiculoIdvehiculo) {
        this.vehiculoIdvehiculo = vehiculoIdvehiculo;
    }

    public int getServicioIdServicio() {
        return servicioIdServicio;
    }

    public void setServicioIdServicio(int servicioIdServicio) {
        this.servicioIdServicio = servicioIdServicio;
    }

    public int getProveedorIdProveedor() {
        return proveedorIdProveedor;
    }

    public void setProveedorIdProveedor(int proveedorIdProveedor) {
        this.proveedorIdProveedor = proveedorIdProveedor;
    }

    public VehiculoDTO getVehiculoDTO() {
        return vehiculoDTO;
    }

    public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
        this.vehiculoDTO = vehiculoDTO;
    }

    public ServicioDTO getServicioDTO() {
        return servicioDTO;
    }

    public void setServicioDTO(ServicioDTO servicioDTO) {
        this.servicioDTO = servicioDTO;
    }

    public ProveedorDTO getProveedorDTO() {
        return proveedorDTO;
    }

    public void setProveedorDTO(ProveedorDTO proveedorDTO) {
        this.proveedorDTO = proveedorDTO;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getFolioFactura() {
        return folioFactura;
    }

    public void setFolioFactura(String folioFactura) {
        this.folioFactura = folioFactura;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
