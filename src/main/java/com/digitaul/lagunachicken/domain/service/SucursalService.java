package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.SucursalDTO;
import com.digitaul.lagunachicken.persistence.crud.ISucursalRepository;
import com.digitaul.lagunachicken.persistence.entity.Sucursal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SucursalDTO> getSucursales() {
        List<SucursalDTO> sucursalDTOS = new ArrayList<>();
        List<Sucursal> sucursales = sucursalRepository.findAll();

        for(Sucursal sucursal : sucursales) {
            sucursalDTOS.add(convertToDTO(sucursal));
        }

        return sucursalDTOS;
    }

    private SucursalDTO convertToDTO(Sucursal sucursal) {
        return modelMapper.map(sucursal, SucursalDTO.class);
    }

}
