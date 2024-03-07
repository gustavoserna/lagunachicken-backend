package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstacionRepository extends JpaRepository<Estacion, Integer> {

    Estacion findByIdEstacion(@Param("idEstacion") int idEstacion);

}
