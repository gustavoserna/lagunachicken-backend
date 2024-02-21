package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    @Procedure(name = "User.registerUser")
    void saveUser(@Param("_username") String username, @Param("_email") String email, @Param("_password") String password, @Param("_role_id_role") int role_id_role);

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
