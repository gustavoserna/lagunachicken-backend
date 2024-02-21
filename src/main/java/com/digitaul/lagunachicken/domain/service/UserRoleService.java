package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.RoleDTO;
import com.digitaul.lagunachicken.persistence.crud.IUserRoleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleService.class);

    @Autowired
    IUserRoleRepository userRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RoleDTO> getUserRoles(String idUsername) {
        List<RoleDTO> userRoles = null;

        try {
            userRoles = userRoleRepository.getUserRoles(idUsername);
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return userRoles;
    }

}
