package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.EstacionDTO;
import com.digitaul.lagunachicken.persistence.crud.IEstacionRepository;
import com.digitaul.lagunachicken.persistence.entity.Estacion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstacionService {

    @Autowired
    private IEstacionRepository estacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EstacionDTO saveEstacion(EstacionDTO estacionDTO) {
        return convertToDTO(estacionRepository.save(convertToEntity(estacionDTO)));
    }

    public EstacionDTO getById(int idEstacion) {
        Estacion estacion = estacionRepository.findByIdEstacion(idEstacion);

        return convertToDTO(estacion);
    }

    public List<EstacionDTO> getEstaciones() {
        List<EstacionDTO> estacionDTOS = new ArrayList<>();
        List<Estacion> estaciones = estacionRepository.findAll();

        for (Estacion estacion : estaciones) {
            estacionDTOS.add(convertToDTO(estacion));
        }

        return estacionDTOS;
    }

    private EstacionDTO convertToDTO(Estacion estacion) {
        return modelMapper.map(estacion, EstacionDTO.class);
    }

    private Estacion convertToEntity(EstacionDTO estacionDTO) {
        return modelMapper.map(estacionDTO, Estacion.class);
    }

}
