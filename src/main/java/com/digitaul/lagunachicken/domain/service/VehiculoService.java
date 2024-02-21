package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoRepository;
import com.digitaul.lagunachicken.persistence.entity.Vehiculo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VehiculoDTO saveVehiculo(VehiculoDTO vehiculoDTO) {
        vehiculoDTO.setChoferIdChofer(vehiculoDTO.getChoferDTO().getIdChofer());
        vehiculoDTO.setSucursalIdSucursal(vehiculoDTO.getSucursalDTO().getIdSucursal());
        return convertToDTO(vehiculoRepository.save(convertToEntity(vehiculoDTO)));
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculoRepository.getVehiculos();
    }

    private VehiculoDTO convertToDTO(Vehiculo vehiculo) {
        return modelMapper.map(vehiculo, VehiculoDTO.class);
    }

    private Vehiculo convertToEntity(VehiculoDTO vehiculoDTO) {
        return modelMapper.map(vehiculoDTO, Vehiculo.class);
    }

}
