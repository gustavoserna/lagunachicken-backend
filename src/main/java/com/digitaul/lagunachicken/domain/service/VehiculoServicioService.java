package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoServicioRepository;
import com.digitaul.lagunachicken.persistence.entity.VehiculoServicio;
import com.digitaul.lagunachicken.utility.Utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoServicioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoServicioService.class);

    @Value("${serlo.app.fileDirectory}")
    private String fileDirectory;

    @Autowired
    private IVehiculoServicioRepository vehiculoServicioRepository;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public VehiculoServicioDTO saveVehiculoServicio(VehiculoServicioDTO vehiculoServicioDTO, MultipartFile file) {
        try {
            String fileName = Utility.generateRandomString(24) + "." + Utility.getFileExtension(file);
            // Define the file path where you want to save the PDF
            String filePath = fileDirectory + fileName;

            // Create an output stream to write the file
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(file.getBytes());
            fos.close();

            vehiculoServicioDTO.setFile(fileName);
            LOGGER.info("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Error occurred while uploading file");
        }

        return convertToDTO(vehiculoServicioRepository.save(convertToEntity(vehiculoServicioDTO)));
    }

    public List<VehiculoServicioDTO> getVehiculosServicios(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT * FROM vehiculo_servicio vs ");
        List<Object[]> objects = getVehiculosServiciosFiltered(queryString, "", filtroDTO);

        List<VehiculoServicioDTO> vehiculoServicioDTOS = convertToVehiculosServiciosDTO(objects);

        return vehiculoServicioDTOS;
    }

    public List<CostoServiciosDTO> getCostoServicios(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT v.*, IFNULL(SUM(vs.costo), 0) AS totalCostoServicio " +
                "FROM vehiculo v " +
                "LEFT JOIN vehiculo_servicio vs ON v.id_vehiculo = vs.vehiculo_id_vehiculo ");

        List<Object[]> objects = getVehiculosServiciosFiltered(queryString, " GROUP BY v.id_vehiculo ", filtroDTO);
        return convertToCostoServiciosDTO(objects);
    }

    public List<ServicioDTO> getIncidenciasServicios(FiltroDTO filtroDTO) {
        StringBuilder queryString = new StringBuilder("SELECT s.*, IFNULL(COUNT(vs.servicio_id_servicio), 0) AS incidenciasServicio " +
                "FROM servicio s " +
                "LEFT JOIN vehiculo_servicio vs ON s.id_servicio = vs.servicio_id_servicio ");

        List<Object[]> objects = getVehiculosServiciosFiltered(queryString, " GROUP by s.id_servicio ", filtroDTO);
        return convertToServiciosDTO(objects);
    }

    // Mappers
    private List<Object[]> getVehiculosServiciosFiltered(StringBuilder queryString, String groupBy, FiltroDTO filtroDTO) {
        String whereANDString = "";
        if (filtroDTO.getIdServicio() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" vs.servicio_id_servicio = :idServicio ");
        }
        if (filtroDTO.getIdVehiculo() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" vs.vehiculo_id_vehiculo = :idVehiculo ");
        }
        if (filtroDTO.getIdChofer() > 0) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            queryString.append(whereANDString).append(" v.chofer_id_chofer = :idChofer ");
        }
        if (!filtroDTO.getFechaDesde().isEmpty() && !filtroDTO.getFechaHasta().isEmpty()) {
            whereANDString = queryString.toString().contains("WHERE") ? " AND " : " WHERE ";
            String fechaNullString = "";
            if(filtroDTO.getIdServicio() == 0 && filtroDTO.getIdVehiculo() == 0 && filtroDTO.getIdChofer() == 0) {
                fechaNullString = " OR vs.fecha_servicio IS NULL ";
            }

            queryString.append(whereANDString).append(" vs.fecha_servicio BETWEEN :fechaDesde AND :fechaHasta ").append(fechaNullString);
        }
        queryString.append(groupBy);
        System.out.println(queryString);

        Query query = entityManager.createNativeQuery(queryString.toString(), Object.class);
        if (filtroDTO.getIdServicio() > 0) {
            query.setParameter("idServicio", filtroDTO.getIdServicio());
        }
        if (filtroDTO.getIdVehiculo() > 0) {
            query.setParameter("idVehiculo", filtroDTO.getIdVehiculo());
        }
        if (filtroDTO.getIdChofer() > 0) {
            query.setParameter("idChofer", filtroDTO.getIdChofer());
        }
        if (!filtroDTO.getFechaDesde().isEmpty() && !filtroDTO.getFechaHasta().isEmpty()) {
            query.setParameter("fechaDesde", filtroDTO.getFechaDesde());
            query.setParameter("fechaHasta", filtroDTO.getFechaHasta());
        }

        return query.getResultList();
    }

    private List<CostoServiciosDTO> convertToCostoServiciosDTO(List<Object[]> results) {
        List<CostoServiciosDTO> costoServiciosDTOS = new ArrayList<>();

        for(Object[] result : results) {
            CostoServiciosDTO costoServiciosDTO = new CostoServiciosDTO();
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

            costoServiciosDTO.setTotalCostoServicios((Double) result[19]);
            costoServiciosDTO.setVehiculoDTO(vehiculoDTO);

            costoServiciosDTOS.add(costoServiciosDTO);
        }

        return costoServiciosDTOS;
    }

    private List<ServicioDTO> convertToServiciosDTO(List<Object[]> results) {
        List<ServicioDTO> servicioDTOS = new ArrayList<>();

        for(Object[] result : results) {
            ServicioDTO servicioDTO = new ServicioDTO();
            servicioDTO.setIdServicio((Integer) result[0]);
            servicioDTO.setServicio((String) result[1]);
            servicioDTO.setIncidencias(Math.toIntExact((Long) result[2]));

            servicioDTOS.add(servicioDTO);
        }

        return servicioDTOS;
    }

    private List<VehiculoServicioDTO> convertToVehiculosServiciosDTO(List<Object[]> results) {
        List<VehiculoServicioDTO> vehiculoServicioDTOS = new ArrayList<>();

        for(Object[] result : results) {
            VehiculoServicioDTO vehiculoServicioDTO = new VehiculoServicioDTO();

            vehiculoServicioDTO.setIdVehiculoServicio((Integer) result[0]);
            VehiculoDTO vehiculoDTO = vehiculoService.getById((Integer) result[1]);
            ServicioDTO servicioDTO = servicioService.getById((Integer) result[2]);
            ProveedorDTO proveedorDTO = proveedorService.getById((Integer) result[3]);
            vehiculoServicioDTO.setKilometraje((String) result[4]);
            vehiculoServicioDTO.setFolioFactura((String) result[5]);
            vehiculoServicioDTO.setCosto((String) result[6]);
            vehiculoServicioDTO.setFechaServicio((String) result[7]);
            vehiculoServicioDTO.setDescripcion((String) result[8]);
            vehiculoServicioDTO.setFile((String) result[9]);

            vehiculoServicioDTO.setVehiculoDTO(vehiculoDTO);
            vehiculoServicioDTO.setServicioDTO(servicioDTO);
            vehiculoServicioDTO.setProveedorDTO(proveedorDTO);

            vehiculoServicioDTOS.add(vehiculoServicioDTO);
        }

        return vehiculoServicioDTOS;
    }

    private VehiculoServicioDTO convertToDTO(VehiculoServicio vehiculoServicio) {
        return modelMapper.map(vehiculoServicio, VehiculoServicioDTO.class);
    }

    private VehiculoServicio convertToEntity(VehiculoServicioDTO vehiculoServicioDTO) {
        return modelMapper.map(vehiculoServicioDTO, VehiculoServicio.class);
    }

}
