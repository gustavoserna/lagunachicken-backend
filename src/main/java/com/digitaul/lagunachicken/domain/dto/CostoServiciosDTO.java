package com.digitaul.lagunachicken.domain.dto;

public class CostoServiciosDTO {

    VehiculoDTO vehiculoDTO;
    double totalCostoServicios;

    public VehiculoDTO getVehiculoDTO() {
        return vehiculoDTO;
    }

    public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
        this.vehiculoDTO = vehiculoDTO;
    }

    public double getTotalCostoServicios() {
        return totalCostoServicios;
    }

    public void setTotalCostoServicios(double totalCostoServicios) {
        this.totalCostoServicios = totalCostoServicios;
    }
}
