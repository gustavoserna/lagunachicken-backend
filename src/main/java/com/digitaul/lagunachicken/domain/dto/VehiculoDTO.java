package com.digitaul.lagunachicken.domain.dto;

import com.digitaul.lagunachicken.persistence.entity.Chofer;

public class VehiculoDTO {

    int idVehiculo;
    int choferIdChofer;
    int sucursalIdSucursal;
    ChoferDTO choferDTO;
    SucursalDTO sucursalDTO;
    String numEconomico;
    String kilometraje;
    String placas;
    String estadoPlacas;
    String modelo;
    String capacidad;
    String marca;
    String tipo;
    String descripcion;
    String numeroSerie;
    String numeroMotor;
    String numeroPoliza;
    String aseguradora;
    String vencimientoPoliza;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getChoferIdChofer() {
        return choferIdChofer;
    }

    public void setChoferIdChofer(int choferIdChofer) {
        this.choferIdChofer = choferIdChofer;
    }

    public int getSucursalIdSucursal() {
        return sucursalIdSucursal;
    }

    public void setSucursalIdSucursal(int sucursalIdSucursal) {
        this.sucursalIdSucursal = sucursalIdSucursal;
    }

    public ChoferDTO getChoferDTO() {
        return choferDTO;
    }

    public void setChoferDTO(ChoferDTO choferDTO) {
        this.choferDTO = choferDTO;
    }

    public SucursalDTO getSucursalDTO() {
        return sucursalDTO;
    }

    public void setSucursalDTO(SucursalDTO sucursalDTO) {
        this.sucursalDTO = sucursalDTO;
    }

    public String getNumEconomico() {
        return numEconomico;
    }

    public void setNumEconomico(String numEconomico) {
        this.numEconomico = numEconomico;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getEstadoPlacas() {
        return estadoPlacas;
    }

    public void setEstadoPlacas(String estadoPlacas) {
        this.estadoPlacas = estadoPlacas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getVencimientoPoliza() {
        return vencimientoPoliza;
    }

    public void setVencimientoPoliza(String vencimientoPoliza) {
        this.vencimientoPoliza = vencimientoPoliza;
    }
}
