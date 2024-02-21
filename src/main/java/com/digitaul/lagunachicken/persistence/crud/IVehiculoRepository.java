package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.domain.dto.ChoferDTO;
import com.digitaul.lagunachicken.domain.dto.SucursalDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.persistence.entity.Vehiculo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {

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
            vehiculoDTO.setPlacas((String) result[4]);
            vehiculoDTO.setEstadoPlacas((String) result[5]);
            vehiculoDTO.setModelo((String) result[6]);
            vehiculoDTO.setCapacidad((String) result[7]);
            vehiculoDTO.setMarca((String) result[8]);
            vehiculoDTO.setTipo((String) result[9]);
            vehiculoDTO.setDescripcion((String) result[10]);
            vehiculoDTO.setNumeroSerie((String) result[11]);
            vehiculoDTO.setNumeroMotor((String) result[12]);
            vehiculoDTO.setNumeroPoliza((String) result[13]);
            vehiculoDTO.setAseguradora((String) result[14]);
            vehiculoDTO.setVencimientoPoliza((String) result[15]);
            vehiculoDTO.setChoferIdChofer((Integer) result[16]);

            sucursalDTO.setSucursal((String) result[17]);

            choferDTO.setIdChofer((Integer) result[18]);
            choferDTO.setNombre((String) result[19]);

            vehiculoDTO.setSucursalDTO(sucursalDTO);
            vehiculoDTO.setChoferDTO(choferDTO);

            vehiculoDTOS.add(vehiculoDTO);
        }

        return vehiculoDTOS;
    }
}
