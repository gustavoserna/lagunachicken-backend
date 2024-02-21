package com.digitaul.lagunachicken.domain.dto;

public class UserRoleDTO {

    public int idUserRole;
    public int userIdUsername;
    public int roleIdRole;

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public int getUserIdUsername() {
        return userIdUsername;
    }

    public void setUserIdUsername(int userIdUsername) {
        this.userIdUsername = userIdUsername;
    }

    public int getRoleIdRole() {
        return roleIdRole;
    }

    public void setRoleIdRole(int roleIdRole) {
        this.roleIdRole = roleIdRole;
    }
}