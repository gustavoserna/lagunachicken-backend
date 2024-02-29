package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vehiculo_servicio")
public class VehiculoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo_servicio")
    private Integer idVehiculoServicio;

    @Column(name = "vehiculo_id_vehiculo")
    private int vehiculoIdVehiculo;

    @Column(name = "servicio_id_servicio")
    private int servicioIdServicio;

    @Column(name = "proveedor_id_proveedor")
    private int proveedorIdProveedor;

    @Column(name = "kilometraje")
    private String kilometraje;

    @Column(name = "folio_factura")
    private String folioFactura;

    @Column(name = "costo")
    private String costo;

    @Column(name = "fecha_servicio")
    private String fechaServicio;

    @Column(name = "descripcion")
    private String descripcion;
}
