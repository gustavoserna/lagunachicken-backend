package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(name = "chofer_id_chofer")
    private int choferIdChofer;

    @Column(name = "sucursal_id_sucursal")
    private int sucursalIdSucursal;

    @Column(name = "num_economico")
    private String numEconomico;

    @Column(name = "kilometraje")
    private String kilometraje;

    @Column(name = "kilometraje_aviso")
    private String kilometrajeAviso;

    @Column(name = "kilometraje_periodo")
    private String kilometrajePeriodo;

    @Column(name = "placas")
    private String placas;

    @Column(name = "estado_placas")
    private String estadoPlacas;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "capacidad")
    private String capacidad;

    @Column(name = "marca")
    private String marca;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "numero_motor")
    private String numeroMotor;

    @Column(name = "numero_poliza")
    private String numeroPoliza;

    @Column(name = "aseguradora")
    private String aseguradora;

    @Column(name = "vencimiento_poliza")
    private String vencimientoPoliza;
}
