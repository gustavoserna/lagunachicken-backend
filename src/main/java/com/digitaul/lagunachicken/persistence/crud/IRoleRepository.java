package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.domain.dto.ERoleDTO;
import com.digitaul.lagunachicken.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERoleDTO name);

}