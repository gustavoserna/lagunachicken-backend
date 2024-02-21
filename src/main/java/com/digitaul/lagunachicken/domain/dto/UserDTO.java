package com.digitaul.lagunachicken.domain.dto;

import java.util.List;

public class UserDTO {

    public int idUsername;
    public String username;
    public String email;
    public String password;
    public List<RoleDTO> roles;

    public UserDTO() {}

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getIdUsername() {
        return idUsername;
    }

    public void setIdUsername(int idUsername) {
        this.idUsername = idUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
