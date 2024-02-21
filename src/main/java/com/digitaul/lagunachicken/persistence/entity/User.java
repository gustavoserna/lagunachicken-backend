package com.digitaul.lagunachicken.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
@NamedStoredProcedureQuery(
        name = "User.registerUser",
        procedureName = "register_user",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "_username", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "_email", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "_password", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "_role_id_role", type = Integer.class)
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_username")
    private Integer idUsername;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
