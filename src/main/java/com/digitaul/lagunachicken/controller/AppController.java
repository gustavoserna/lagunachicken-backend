package com.digitaul.lagunachicken.controller;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.domain.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app")
public class AppController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private ChoferService choferService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private VehiculoServicioService vehiculoServicioService;

    // CHOFER
    @PostMapping("chofer")
    public ChoferDTO saveChofer(@RequestBody ChoferDTO choferDTO){
        return choferService.saveChofer(choferDTO);
    }

    @GetMapping("chofer/findAll")
    public List<ChoferDTO> getChoferes() {
        return choferService.getChoferes();
    }

    // SUCURSAL
    @GetMapping("sucursal/findAll")
    public List<SucursalDTO> getSucursales() {
        return sucursalService.getSucursales();
    }

    // SERVICIO
    @PostMapping("servicio")
    public ServicioDTO saveServicio(@RequestBody ServicioDTO servicioDTO) {
        return servicioService.saveServicio(servicioDTO);
    }

    @GetMapping("servicio/findAll")
    public List<ServicioDTO> getServicios() {
        return  servicioService.getServicios();
    }

    // PROVEEDOR
    @PostMapping("proveedor")
    public ProveedorDTO saveProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return proveedorService.saveProveedor(proveedorDTO);
    }

    @GetMapping("proveedor/findAll")
    public List<ProveedorDTO> getProveedores() {
        return  proveedorService.getProveedores();
    }

    // VEHICULO
    @PostMapping("vehiculo")
    public VehiculoDTO saveVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        return vehiculoService.saveVehiculo(vehiculoDTO);
    }

    @GetMapping("vehiculo/findAll")
    public List<VehiculoDTO> getVehiculos() {
        return vehiculoService.getVehiculos();
    }

    @PostMapping("vehiculo/servicio")
    public VehiculoServicioDTO saveVehiculoServicio(@RequestBody VehiculoServicioDTO vehiculoServicioDTO) {
        return vehiculoServicioService.saveVehiculoServicio(vehiculoServicioDTO);
    }

    @GetMapping("vehiculo/servicio/findAll")
    public List<VehiculoServicioDTO> getVehiculosServicios() {
        return vehiculoServicioService.getVehiculosServicios();
    }

}
