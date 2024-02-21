package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.domain.dto.ERoleDTO;
import com.digitaul.lagunachicken.domain.dto.RoleDTO;
import com.digitaul.lagunachicken.persistence.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

    @Transactional
    @Modifying
    @Query(
            value = "SELECT r.id_role as roleId, r.name as name FROM user_role as ur " +
                    "LEFT JOIN role as r ON r.id_role = ur.role_id_role " +
                    "WHERE ur.user_id_username = :idUsername",
            nativeQuery = true
    )
    List<Object[]> getUserRolesRaw(@Param("idUsername") String idUsername);

    // Método para mapear los resultados a una lista de RoleDTO
    default List<RoleDTO> getUserRoles(@Param("idUsername") String idUsername) {
        List<Object[]> results = getUserRolesRaw(idUsername);
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Object[] result : results) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId((Integer) result[0]);
            roleDTO.setName(ERoleDTO.valueOf((String) result[1]));
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

}

