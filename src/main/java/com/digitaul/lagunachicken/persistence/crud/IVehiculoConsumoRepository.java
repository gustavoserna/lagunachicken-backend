package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.VehiculoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoConsumoRepository extends JpaRepository<VehiculoConsumo, Integer> {

    @Query(
            value = "SELECT COUNT(*) FROM vehiculo_consumo WHERE despacho = :despacho",
            nativeQuery = true
    )
    int isDespachoPresent(@Param("despacho") String despacho);

}
