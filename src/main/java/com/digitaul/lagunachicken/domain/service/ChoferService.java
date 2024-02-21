package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.ChoferDTO;
import com.digitaul.lagunachicken.persistence.crud.IChoferRepository;
import com.digitaul.lagunachicken.persistence.entity.Chofer;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChoferService.class);

    @Autowired
    private IChoferRepository choferRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ChoferDTO saveChofer(ChoferDTO choferDTO) {
        Chofer chofer = choferRepository.save(convertToEntity(choferDTO));
        return convertToDTO(chofer);
    }

    public List<ChoferDTO> getChoferes() {
        List<ChoferDTO> choferDTOS = new ArrayList<>();
        List<Chofer> choferes = choferRepository.findAll();

        for(Chofer chofer : choferes) {
            choferDTOS.add(convertToDTO(chofer));
        }

        return choferDTOS;
    }

    private ChoferDTO convertToDTO(Chofer chofer) {
        return modelMapper.map(chofer, ChoferDTO.class);
    }

    private Chofer convertToEntity(ChoferDTO choferDTO) {
        return modelMapper.map(choferDTO, Chofer.class);
    }
}
