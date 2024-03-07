package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoConsumoRepository;
import com.digitaul.lagunachicken.persistence.entity.VehiculoConsumo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoConsumoService {

    @Autowired
    private IVehiculoConsumoRepository vehiculoConsumoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private EntityManager entityManager;

    public VehiculoConsumoDTO saveVehiculoConsumo(VehiculoConsumoDTO vehiculoConsumoDTO) {
        return convertToDTO(vehiculoConsumoRepository.save(convertToEntity(vehiculoConsumoDTO)));
    }

    public List<VehiculoConsumoDTO> getVehiculosConsumos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM vehiculo_consumo vc ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, "", filtroDTO);
        List<VehiculoConsumoDTO> vehiculoConsumoDTOS = convertToVehiculosConsumosDTO(objects);

        return vehiculoConsumoDTOS;
    }

    public List<CostoConsumosDTO> getCostoConsumos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT v.*, IFNULL(SUM(vc.monto), 0) AS totalMontosConsumos " +
                "FROM vehiculo v " +
                "LEFT JOIN vehiculo_consumo vc ON v.id_vehiculo = vc.vehiculo_id_vehiculo ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, " GROUP BY v.id_vehiculo ", filtroDTO);
        return convertToCostoConsumosDTO(objects);
    }

    public List<EstacionDTO> getIncidenciasEstaciones(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT e.*, IFNULL(COUNT(vc.estacion_id_estacion), 0) AS incidenciasEstacion " +
                "FROM estacion e " +
                "LEFT JOIN vehiculo_consumo vc ON e.id_estacion = vc.estacion_id_estacion ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, " GROUP by e.id_estacion ", filtroDTO);
        return convertToEstacionesDTO(objects);
    }

    public List<ProductoDTO> getIncidenciasProductos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT p.*, IFNULL(SUM(vc.cantidad), 0) AS incidenciasLitrosProducto " +
                "FROM producto p " +
                "LEFT JOIN vehiculo_consumo vc ON p.id_producto = vc.producto_id_producto ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, " GROUP by p.id_producto ", filtroDTO);
        return convertToProductosDTO(objects);
    }

    // Mappers
    private List<Object[]> getVehiculosConsumosFiltered(StringBuilder queryString, String groupBy, FiltroDTO filtroDTO) {
        String whereANDString = "";
        if (filtroDTO.getIdVehiculo() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" vc.vehiculo_id_vehiculo = :idVehiculo ");
        }
        if(filtroDTO.getIdEstacion() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" vc.estacion_id_estacion = :idEstacion ");
        }
        if(filtroDTO.getIdProducto() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" vc.producto_id_producto = :idProducto ");
        }
        if (!filtroDTO.getFechaDesde().isEmpty() && !filtroDTO.getFechaHasta().isEmpty()) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            String fechaNullString = "";
            if(filtroDTO.getIdVehiculo() == 0 && filtroDTO.getIdEstacion() == 0 && filtroDTO.getIdProducto() == 0) {
                fechaNullString = " OR vc.fecha_consumo IS NULL ";
            }

            queryString.append(whereANDString).append(" vc.fecha_consumo BETWEEN :fechaDesde AND :fechaHasta ").append(fechaNullString);
        }
        queryString.append(groupBy);

        Query query = entityManager.createNativeQuery(queryString.toString(), Object.class);
        if (filtroDTO.getIdVehiculo() > 0) {
            query.setParameter("idVehiculo", filtroDTO.getIdVehiculo());
        }
        if (filtroDTO.getIdEstacion() > 0) {
            query.setParameter("idEstacion", filtroDTO.getIdEstacion());
        }
        if (filtroDTO.getIdProducto() > 0) {
            query.setParameter("idProducto", filtroDTO.getIdProducto());
        }
        if (!filtroDTO.getFechaDesde().isEmpty() && !filtroDTO.getFechaHasta().isEmpty()) {
            query.setParameter("fechaDesde", filtroDTO.getFechaDesde());
            query.setParameter("fechaHasta", filtroDTO.getFechaHasta());
        }

        return query.getResultList();
    }

    private List<CostoConsumosDTO> convertToCostoConsumosDTO(List<Object[]> results) {
        List<CostoConsumosDTO> costoConsumosDTOS = new ArrayList<>();

        for (Object[] result : results) {
            CostoConsumosDTO costoConsumosDTO = new CostoConsumosDTO();
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

            costoConsumosDTO.setTotalMontosConsumos((Double) result[17]);
            costoConsumosDTO.setVehiculoDTO(vehiculoDTO);

            costoConsumosDTOS.add(costoConsumosDTO);
        }

        return costoConsumosDTOS;
    }

    private List<EstacionDTO> convertToEstacionesDTO(List<Object[]> results) {
        List<EstacionDTO> estacionDTOS = new ArrayList<>();

        for(Object[] result : results) {
            EstacionDTO estacionDTO = new EstacionDTO();
            estacionDTO.setIdEstacion((Integer) result[0]);
            estacionDTO.setEstacion((String) result[1]);
            estacionDTO.setIncidencias(Math.toIntExact((Long) result[2]));

            estacionDTOS.add(estacionDTO);
        }

        return estacionDTOS;
    }

    private List<ProductoDTO> convertToProductosDTO(List<Object[]> results) {
        List<ProductoDTO> productoDTOS = new ArrayList<>();

        for(Object[] result : results) {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIdProducto((Integer) result[0]);
            productoDTO.setProducto((String) result[1]);
            productoDTO.setIncidenciasLitrosProducto((Double) result[2]);

            productoDTOS.add(productoDTO);
        }

        return productoDTOS;
    }

    private List<VehiculoConsumoDTO> convertToVehiculosConsumosDTO(List<Object[]> results) {
        List<VehiculoConsumoDTO> vehiculoConsumoDTOS = new ArrayList<>();

        for(Object[] result : results) {
            VehiculoConsumoDTO vehiculoConsumoDTO = new VehiculoConsumoDTO();

            vehiculoConsumoDTO.setIdVehiculoConsumo((Integer) result[0]);
            VehiculoDTO vehiculoDTO = vehiculoService.getById((Integer) result[1]);
            EstacionDTO estacionDTO = estacionService.getById((Integer) result[2]);
            ProductoDTO productoDTO = productoService.getById((Integer) result[3]);
            vehiculoConsumoDTO.setOdometro((String) result[4]);
            vehiculoConsumoDTO.setRecorrido((String) result[5]);
            vehiculoConsumoDTO.setRendimiento((String) result[6]);
            vehiculoConsumoDTO.setCantidad((String) result[7]);
            vehiculoConsumoDTO.setPrecio((String) result[8]);
            vehiculoConsumoDTO.setMonto((String) result[9]);
            vehiculoConsumoDTO.setHoraConsumo((String) result[10]);
            vehiculoConsumoDTO.setFechaConsumo((String) result[11]);

            vehiculoConsumoDTO.setVehiculoDTO(vehiculoDTO);
            vehiculoConsumoDTO.setEstacionDTO(estacionDTO);
            vehiculoConsumoDTO.setProductoDTO(productoDTO);

            vehiculoConsumoDTOS.add(vehiculoConsumoDTO);
        }

        return vehiculoConsumoDTOS;
    }

    private VehiculoConsumoDTO convertToDTO(VehiculoConsumo vehiculoConsumo) {
        return modelMapper.map(vehiculoConsumo, VehiculoConsumoDTO.class);
    }

    private VehiculoConsumo convertToEntity(VehiculoConsumoDTO vehiculoConsumoDTO) {
        return modelMapper.map(vehiculoConsumoDTO, VehiculoConsumo.class);
    }

}
