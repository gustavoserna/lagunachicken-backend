package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.VehiculoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoConsumoRepository extends JpaRepository<VehiculoConsumo, Integer> {
}
