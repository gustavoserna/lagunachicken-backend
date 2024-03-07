package com.digitaul.lagunachicken.domain.dto;

public class ServicioDTO {

    int idServicio;
    String servicio;
    int incidencias; //argumento para query de reportes

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(int incidencias) {
        this.incidencias = incidencias;
    }
}
