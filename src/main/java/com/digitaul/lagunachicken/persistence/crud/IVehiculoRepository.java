package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.domain.dto.ChoferDTO;
import com.digitaul.lagunachicken.domain.dto.SucursalDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.persistence.entity.Vehiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Vehiculo findByIdVehiculo(@Param("idVehiculo") int idVehiculo);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE vehiculo SET kilometraje = :kilometraje WHERE id_vehiculo = :idVehiculo",
            nativeQuery = true
    )
    void updateKilometraje(@Param("idVehiculo") int idVehiculo, @Param("kilometraje") String kilometraje);

    @Transactional
    @Modifying
    @Query(
            value = "SELECT v.*, s.sucursal, c.id_chofer, c.nombre FROM vehiculo as v " +
                    "LEFT JOIN sucursal as s ON s.id_sucursal = v.sucursal_id_sucursal " +
                    "LEFT JOIN chofer as c ON c.id_chofer = v.chofer_id_chofer " +
                    "ORDER by v.id_vehiculo DESC",
            nativeQuery = true
    )
    List<Object[]> getVehiculosRaw();

    default List<VehiculoDTO> getVehiculos() {
        List<Object[]> results = getVehiculosRaw();
        List<VehiculoDTO> vehiculoDTOS = new ArrayList<>();

        for(Object[] result : results) {
            VehiculoDTO vehiculoDTO = new VehiculoDTO();
            SucursalDTO sucursalDTO = new SucursalDTO();
            ChoferDTO choferDTO = new ChoferDTO();

            vehiculoDTO.setIdVehiculo((Integer) result[0]);
            vehiculoDTO.setSucursalIdSucursal((Integer) result[1]);
            vehiculoDTO.setNumEconomico((String) result[2]);
            vehiculoDTO.setKilometraje((String) result[3]);
            vehiculoDTO.setKilometrajeAviso((String) result[4]);
            vehiculoDTO.setKilometrajePeriodo((String) result[5]);
            vehiculoDTO.setPlacas((String) result[6]);
            vehiculoDTO.setEstadoPlacas((String) result[7]);
            vehiculoDTO.setModelo((String) result[8]);
            vehiculoDTO.setCapacidad((String) result[9]);
            vehiculoDTO.setMarca((String) result[10]);
            vehiculoDTO.setTipo((String) result[11]);
            vehiculoDTO.setDescripcion((String) result[12]);
            vehiculoDTO.setNumeroSerie((String) result[13]);
            vehiculoDTO.setNumeroMotor((String) result[14]);
            vehiculoDTO.setNumeroPoliza((String) result[15]);
            vehiculoDTO.setAseguradora((String) result[16]);
            vehiculoDTO.setVencimientoPoliza((String) result[17]);
            vehiculoDTO.setChoferIdChofer((Integer) result[18]);

            sucursalDTO.setSucursal((String) result[19]);

            choferDTO.setIdChofer((Integer) result[20]);
            choferDTO.setNombre((String) result[21]);

            vehiculoDTO.setSucursalDTO(sucursalDTO);
            vehiculoDTO.setChoferDTO(choferDTO);

            vehiculoDTOS.add(vehiculoDTO);
        }

        return vehiculoDTOS;
    }
}
