package com.digitaul.lagunachicken.security.services;

import com.digitaul.lagunachicken.domain.dto.ERoleDTO;
import com.digitaul.lagunachicken.domain.dto.RoleDTO;
import com.digitaul.lagunachicken.persistence.crud.IRoleRepository;
import com.digitaul.lagunachicken.persistence.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IRoleRepository roleRepository;

    public RoleDTO findByName(ERoleDTO name) {
        Optional<Role> roleOp = roleRepository.findByName(name);
        Role role = null;

        try {
            role = roleOp.orElseThrow(() -> new NoSuchElementException("The role type was not found."));
        } catch (NoSuchElementException e) {
            LOGGER.error(e.getMessage());
            throw new NoSuchElementException(e.getMessage());
        }

        return  convertToDTO(role);
    }

    private RoleDTO convertToDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }
}