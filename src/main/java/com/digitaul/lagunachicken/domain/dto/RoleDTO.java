package com.digitaul.lagunachicken.domain.dto;

public class RoleDTO {

    int roleId;
    ERoleDTO name;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ERoleDTO getName() {
        return name;
    }

    public void setName(ERoleDTO name) {
        this.name = name;
    }
}