package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.ProveedorDTO;
import com.digitaul.lagunachicken.domain.dto.ServicioDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoServicioDTO;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoServicioRepository;
import com.digitaul.lagunachicken.persistence.entity.VehiculoServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoServicioService {

    @Autowired
    private IVehiculoServicioRepository vehiculoServicioRepository;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ModelMapper modelMapper;

    public VehiculoServicioDTO saveVehiculoServicio(VehiculoServicioDTO vehiculoServicioDTO) {
        return convertToDTO(vehiculoServicioRepository.save(convertToEntity(vehiculoServicioDTO)));
    }

    public List<VehiculoServicioDTO> getVehiculosServicios() {
        List<VehiculoServicio> vehiculoServicios = vehiculoServicioRepository.findAll();
        List<VehiculoServicioDTO> vehiculoServicioDTOS = new ArrayList<>();

        for (VehiculoServicio vehiculoServicio : vehiculoServicios) {
            VehiculoServicioDTO vehiculoServicioDTO = convertToDTO(vehiculoServicio);
            VehiculoDTO vehiculoDTO = vehiculoService.getById(vehiculoServicioDTO.getVehiculoIdvehiculo());
            ServicioDTO servicioDTO = servicioService.getById(vehiculoServicioDTO.getServicioIdServicio());
            ProveedorDTO proveedorDTO = proveedorService.getById(vehiculoServicioDTO.getProveedorIdProveedor());

            vehiculoServicioDTO.setVehiculoDTO(vehiculoDTO);
            vehiculoServicioDTO.setServicioDTO(servicioDTO);
            vehiculoServicioDTO.setProveedorDTO(proveedorDTO);

            vehiculoServicioDTOS.add(vehiculoServicioDTO);
        }

        return vehiculoServicioDTOS;
    }

    private VehiculoServicioDTO convertToDTO(VehiculoServicio vehiculoServicio) {
        return modelMapper.map(vehiculoServicio, VehiculoServicioDTO.class);
    }

    private VehiculoServicio convertToEntity(VehiculoServicioDTO vehiculoServicioDTO) {
        return modelMapper.map(vehiculoServicioDTO, VehiculoServicio.class);
    }

}
