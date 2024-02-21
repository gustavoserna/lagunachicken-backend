package com.digitaul.lagunachicken.persistence.entity;

import com.digitaul.lagunachicken.domain.dto.ERoleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRrol;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoleDTO name;

    public Role() {

    }

    public Role(ERoleDTO name) {
        this.name = name;
    }

}