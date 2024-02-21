package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.ServicioDTO;
import com.digitaul.lagunachicken.persistence.crud.IServicioRepository;
import com.digitaul.lagunachicken.persistence.entity.Servicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ServicioDTO saveServicio(ServicioDTO servicioDTO) {
        return convertToDTO(servicioRepository.save(convertToEntity(servicioDTO)));
    }

    public List<ServicioDTO> getServicios() {
        List<ServicioDTO> servicioDTOS = new ArrayList<>();
        List<Servicio> servicios = servicioRepository.findAll();

        for(Servicio servicio : servicios) {
            servicioDTOS.add(convertToDTO(servicio));
        }

        return servicioDTOS;
    }

    private ServicioDTO convertToDTO(Servicio servicio) {
        return  modelMapper.map(servicio, ServicioDTO.class);
    }

    private Servicio convertToEntity(ServicioDTO servicioDTO) {
        return modelMapper.map(servicioDTO, Servicio.class);
    }

}
