package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role")
    private Integer idUserRole;

    @Column(name = "user_id_username")
    private Integer userIdUsername;

    @Column(name = "role_id_role")
    private Integer roleIdRole;

}
