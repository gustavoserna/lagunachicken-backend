package com.digitaul.lagunachicken.security.services;

import com.digitaul.lagunachicken.domain.dto.UserDTO;
import com.digitaul.lagunachicken.domain.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    public void saveUser(UserDTO userDTO){
        userService.saveUser(userDTO);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO userDTO = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(userDTO);
    }

    public Boolean existsByUsername(String username){
        return userService.existsByUsername(username);
    }

    public Boolean existsByEmail(String email){
        return userService.existsByEmail(email);
    }
}
