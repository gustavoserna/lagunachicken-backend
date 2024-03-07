package com.digitaul.lagunachicken.persistence.crud;

import com.digitaul.lagunachicken.domain.dto.CostoServiciosDTO;
import com.digitaul.lagunachicken.domain.dto.FiltroDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.persistence.entity.VehiculoServicio;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IVehiculoServicioRepository extends JpaRepository<VehiculoServicio, Integer> {

    @Transactional
    @Modifying
    @Query(
            value = "SELECT v.*, SUM(vs.costo) AS total_costo_servicio " +
                    "FROM vehiculo v " +
                    "LEFT JOIN vehiculo_servicio vs ON v.id_vehiculo = vs.vehiculo_id_vehiculo " +
                    "WHERE vs.servicio_id_servicio = :idServicio " +
                    "AND vs.vehiculo_id_vehiculo = :idVehiculo " +
                    "AND v.chofer_id_chofer = :idChofer " +
                    "AND vs.fecha_servicio BETWEEN :fechaDesde AND :fechaHasta",
            nativeQuery = true
    )
    List<Object[]> getCostoServiciosRaw(
            @Param("idServicio") int idServicio,
            @Param("idVehiculo") int idVehiculo,
            @Param("idChofer") int idChofer,
            @Param("fechaDesde") String fechaDesde,
            @Param("fechaHasta") String fechaHasta);

    default List<CostoServiciosDTO> getCostoServicios(FiltroDTO filtroDTO) {
        List<Object[]> results = getCostoServiciosRaw(
                filtroDTO.getIdServicio(),
                filtroDTO.getIdVehiculo(),
                filtroDTO.getIdChofer(),
                filtroDTO.getFechaDesde(),
                filtroDTO.getFechaHasta()
        );

        List<CostoServiciosDTO> costoServiciosDTOS = new ArrayList<>();

        for(Object[] result : results) {
            CostoServiciosDTO costoServiciosDTO = new CostoServiciosDTO();
            VehiculoDTO vehiculoDTO = new VehiculoDTO();

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

            costoServiciosDTO.setTotalCostoServicios((Double) result[17]);
            costoServiciosDTO.setVehiculoDTO(vehiculoDTO);

            costoServiciosDTOS.add(costoServiciosDTO);
        }

        return costoServiciosDTOS;
    }
}
