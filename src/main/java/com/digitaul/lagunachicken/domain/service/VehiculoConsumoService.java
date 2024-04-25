package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.exception.types.DuplicatedEntryException;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoConsumoRepository;
import com.digitaul.lagunachicken.persistence.entity.VehiculoConsumo;
import com.digitaul.lagunachicken.utility.Utility;
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

        // actualizar kilometraje del vehiculo y checar si se requiere mantenimiento preventivo
        vehiculoService.servicioRequerido(vehiculoConsumoDTO);

        // calcular monto
        vehiculoConsumoDTO.setMonto( String.valueOf( Double.parseDouble(vehiculoConsumoDTO.getPrecio()) * Double.parseDouble(vehiculoConsumoDTO.getCantidad()) ) );

        // validar numero de despacho
        if(vehiculoConsumoDTO.getDespacho().isEmpty()) {
            vehiculoConsumoDTO.setDespacho(Utility.generateRandomString(15));
        } else {
            if(isDespachoPresent(vehiculoConsumoDTO.getDespacho()) > 0) {
                throw new DuplicatedEntryException("Num. despacho " + vehiculoConsumoDTO.getDespacho());
            }
        }

        return convertToDTO(vehiculoConsumoRepository.save(convertToEntity(vehiculoConsumoDTO)));
    }

    public List<VehiculoConsumoDTO> getVehiculosConsumos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM vehiculo_consumo vc ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, "", filtroDTO);
        List<VehiculoConsumoDTO> vehiculoConsumoDTOS = convertToVehiculosConsumosDTO(objects);

        return vehiculoConsumoDTOS;
    }

    public List<CostoConsumosDTO> getCostoConsumos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT v.*, IFNULL(SUM(vc.monto), 0) AS totalMontosConsumos, IFNULL(AVG(vc.rendimiento), 0) AS promedioRendimiento " +
                "FROM vehiculo v " +
                "LEFT JOIN vehiculo_consumo vc ON v.id_vehiculo = vc.vehiculo_id_vehiculo ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, " GROUP BY v.id_vehiculo ", filtroDTO);
        return convertToCostoConsumosDTO(objects);
    }

    public List<ProductoDTO> getIncidenciasProductos(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT p.*, IFNULL(SUM(vc.cantidad), 0) AS incidenciasLitrosProducto " +
                "FROM producto p " +
                "LEFT JOIN vehiculo_consumo vc ON p.id_producto = vc.producto_id_producto ");

        List<Object[]> objects = getVehiculosConsumosFiltered(queryString, " GROUP by p.id_producto ", filtroDTO);
        return convertToProductosDTO(objects);
    }

    public int isDespachoPresent(String despacho) {
        return vehiculoConsumoRepository.isDespachoPresent(despacho);
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

            costoConsumosDTO.setTotalMontosConsumos((Double) result[19]);
            costoConsumosDTO.setRendimientoPromedio((Double) result[20]);
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
            vehiculoConsumoDTO.setDespacho((String) result[1]);
            VehiculoDTO vehiculoDTO = vehiculoService.getById((Integer) result[2]);
            EstacionDTO estacionDTO = estacionService.getById((Integer) result[3]);
            ProductoDTO productoDTO = productoService.getById((Integer) result[4]);
            vehiculoConsumoDTO.setOdometro((String) result[5]);
            vehiculoConsumoDTO.setRecorrido((String) result[6]);
            vehiculoConsumoDTO.setRendimiento((String) result[7]);
            vehiculoConsumoDTO.setCantidad((String) result[8]);
            vehiculoConsumoDTO.setPrecio((String) result[9]);
            vehiculoConsumoDTO.setMonto((String) result[10]);
            vehiculoConsumoDTO.setHoraConsumo((String) result[11]);
            vehiculoConsumoDTO.setFechaConsumo((String) result[12]);

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
