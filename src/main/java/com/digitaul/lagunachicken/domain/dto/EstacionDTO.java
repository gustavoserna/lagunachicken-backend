package com.digitaul.lagunachicken.domain.dto;

public class EstacionDTO {

    int idEstacion;
    String estacion;
    int incidencias; //argumento para query de reportes

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public int getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(int incidencias) {
        this.incidencias = incidencias;
    }
}
