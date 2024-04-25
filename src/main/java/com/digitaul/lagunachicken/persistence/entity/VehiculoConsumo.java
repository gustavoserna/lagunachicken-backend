package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "vehiculo_consumo")
public class VehiculoConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo_consumo")
    private Integer idVehiculoConsumo;

    @Column(name = "despacho")
    private String despacho;

    @Column(name = "vehiculo_id_vehiculo")
    private int vehiculoIdVehiculo;

    @Column(name = "estacion_id_estacion")
    private int estacionIdEstacion;

    @Column(name = "producto_id_producto")
    private int productoIdProducto;

    @Column(name = "odometro")
    private String odometro;

    @Column(name = "recorrido")
    private String recorrido;

    @Column(name = "rendimiento")
    private String rendimiento;

    @Column(name = "cantidad")
    private String cantidad;

    @Column(name = "precio")
    private String precio;

    @Column(name = "monto")
    private String monto;

    @Column(name = "hora_consumo")
    private String horaConsumo;

    @Column(name = "fecha_consumo")
    private String fechaConsumo;

}
