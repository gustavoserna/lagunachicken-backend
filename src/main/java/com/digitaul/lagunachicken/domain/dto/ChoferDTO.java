package com.digitaul.lagunachicken.domain.dto;

public class ChoferDTO {

    int idChofer;
    String nombre;
    String fechaNacimiento;
    String direccion;
    String nss;
    String vencimientoLicencia;
    String tipoSangre;
    String foto;

    public int getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(int idChofer) {
        this.idChofer = idChofer;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getVencimientoLicencia() {
        return vencimientoLicencia;
    }

    public void setVencimientoLicencia(String vencimientoLicencia) {
        this.vencimientoLicencia = vencimientoLicencia;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
