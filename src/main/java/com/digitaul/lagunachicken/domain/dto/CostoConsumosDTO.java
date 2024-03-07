package com.digitaul.lagunachicken.domain.dto;

public class CostoConsumosDTO {

    VehiculoDTO vehiculoDTO;
    double totalMontosConsumos;

    public VehiculoDTO getVehiculoDTO() {
        return vehiculoDTO;
    }

    public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
        this.vehiculoDTO = vehiculoDTO;
    }

    public double getTotalMontosConsumos() {
        return totalMontosConsumos;
    }

    public void setTotalMontosConsumos(double totalMontosConsumos) {
        this.totalMontosConsumos = totalMontosConsumos;
    }
}
