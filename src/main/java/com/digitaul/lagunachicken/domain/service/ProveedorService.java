package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.ProveedorDTO;
import com.digitaul.lagunachicken.persistence.crud.IProveedorRepository;
import com.digitaul.lagunachicken.persistence.entity.Proveedor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private IProveedorRepository proveedorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProveedorDTO saveProveedor(ProveedorDTO proveedorDTO) {
        return convertToDTO(proveedorRepository.save(convertToEntity(proveedorDTO)));
    }

    public ProveedorDTO getById(int idProveedor) {
        Proveedor proveedor = proveedorRepository.findByIdProveedor(idProveedor);

        return convertToDTO(proveedor);
    }

    public List<ProveedorDTO> getProveedores() {
        List<ProveedorDTO> proveedorDTOS = new ArrayList<>();
        List<Proveedor> proveedores = proveedorRepository.findAll();

        for (Proveedor proveedor : proveedores) {
            proveedorDTOS.add(convertToDTO(proveedor));
        }

        return proveedorDTOS;
    }

    private ProveedorDTO convertToDTO(Proveedor proveedor) {
        return modelMapper.map(proveedor, ProveedorDTO.class);
    }

    private Proveedor convertToEntity(ProveedorDTO proveedorDTO) {
        return modelMapper.map(proveedorDTO, Proveedor.class);
    }

}
