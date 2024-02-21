package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.RoleDTO;
import com.digitaul.lagunachicken.domain.dto.UserDTO;
import com.digitaul.lagunachicken.persistence.crud.IUserRepository;
import com.digitaul.lagunachicken.persistence.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRoleService userRoleService;

    public void saveUser(UserDTO userDTO) {
        RoleDTO roleDTO = userDTO.getRoles().getFirst();
        userRepository.saveUser(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                roleDTO.getRoleId()
        );
    }

    public Optional<UserDTO> findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        UserDTO userDTO = convertToDTO(user);
        List<RoleDTO> userRoles = userRoleService.getUserRoles(String.valueOf(userDTO.getIdUsername()));
        userDTO.setRoles(userRoles);

        return Optional.of(userDTO);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
