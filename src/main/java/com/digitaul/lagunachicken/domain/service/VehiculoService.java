package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.MailDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoConsumoDTO;
import com.digitaul.lagunachicken.domain.dto.VehiculoDTO;
import com.digitaul.lagunachicken.persistence.crud.IVehiculoRepository;
import com.digitaul.lagunachicken.persistence.entity.Vehiculo;
import com.digitaul.lagunachicken.utility.MailUtility;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VehiculoDTO saveVehiculo(VehiculoDTO vehiculoDTO) {
        vehiculoDTO.setChoferIdChofer(vehiculoDTO.getChoferDTO().getIdChofer());
        vehiculoDTO.setSucursalIdSucursal(vehiculoDTO.getSucursalDTO().getIdSucursal());
        return convertToDTO(vehiculoRepository.save(convertToEntity(vehiculoDTO)));
    }

    public VehiculoDTO getById(int idVehiculo) {
        Vehiculo vehiculo = vehiculoRepository.findByIdVehiculo(idVehiculo);

        return convertToDTO(vehiculo);
    }

    public void servicioRequerido(VehiculoConsumoDTO vehiculoConsumoDTO) {
        VehiculoDTO vehiculoDTO = getById(vehiculoConsumoDTO.getVehiculoIdVehiculo());

        // primero actualizar kilometraje
        vehiculoRepository.updateKilometraje(vehiculoDTO.getIdVehiculo(), vehiculoConsumoDTO.getOdometro());

        double hitosAlcanzados = Math.ceil(Double.parseDouble(vehiculoConsumoDTO.getOdometro()) / 10000);
        double proximoHitoKilometraje = hitosAlcanzados * 10000;
        double kilometrosFaltantes = proximoHitoKilometraje - Double.parseDouble(vehiculoConsumoDTO.getOdometro());

        if(kilometrosFaltantes <= Double.parseDouble(vehiculoDTO.getKilometrajeAviso())) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setAsunto(vehiculoDTO.getNumEconomico() + " - " + "Mantenimiento preventivo requerido");
            mailDTO.setCuerpo(
                    "Mantenimiento preventivo requerido para la unidad " + vehiculoDTO.getNumEconomico() + "\n" +
                    "Odómetro: " + vehiculoConsumoDTO.getOdometro() + "\n" +
                    "Kilometros faltantes para el mantenimiento: " + kilometrosFaltantes + "\n"
            );

            MailUtility.sendEmail(mailDTO);
        }
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculoRepository.getVehiculos();
    }

    private VehiculoDTO convertToDTO(Vehiculo vehiculo) {
        return modelMapper.map(vehiculo, VehiculoDTO.class);
    }

    private Vehiculo convertToEntity(VehiculoDTO vehiculoDTO) {
        return modelMapper.map(vehiculoDTO, Vehiculo.class);
    }

}
