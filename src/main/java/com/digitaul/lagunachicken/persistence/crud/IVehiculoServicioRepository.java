package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.persistence.entity.VehiculoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehiculoServicioRepository extends JpaRepository<VehiculoServicio, Integer> {

    //List<VehiculoServicio> findAllOrderByIdVehiculoServicioDesc();

}
