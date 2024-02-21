package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Integer> {
}
