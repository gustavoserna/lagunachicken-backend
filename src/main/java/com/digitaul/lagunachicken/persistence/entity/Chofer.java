package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chofer")
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chofer")
    private Integer idChofer;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "nss")
    private String nss;

    @Column(name = "vencimiento_licencia")
    private String vencimientoLicencia;

    @Column(name = "tipo_sangre")
    private String tipoSangre;

    @Column(name = "foto")
    private String foto;
}
