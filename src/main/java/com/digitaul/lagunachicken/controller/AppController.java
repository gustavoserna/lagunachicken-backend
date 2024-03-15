package com.digitaul.lagunachicken.controller;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.domain.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
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

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VehiculoConsumoService vehiculoConsumoService;

    // CHOFER
    @PostMapping("chofer")
    public ChoferDTO saveChofer(@RequestBody ChoferDTO choferDTO){
        return choferService.saveChofer(choferDTO);
    }

    @GetMapping("chofer/findAll")
    public List<ChoferDTO> getChoferes() {
        return choferService.getChoferes();
    }

    // ESTACION
    @PostMapping("estacion")
    public EstacionDTO saveEstacion(@RequestBody EstacionDTO estacionDTO){
        return estacionService.saveEstacion(estacionDTO);
    }

    @GetMapping("estacion/findAll")
    public List<EstacionDTO> getEstaciones() {
        return estacionService.getEstaciones();
    }

    // PRODUCTO
    @PostMapping("producto")
    public ProductoDTO saveProducto(@RequestBody ProductoDTO productoDTO){
        return productoService.saveProducto(productoDTO);
    }

    @GetMapping("producto/findAll")
    public List<ProductoDTO> getProductos() {
        return productoService.getProductos();
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
    @PreAuthorize("hasRole('ADMIN')")
    public VehiculoServicioDTO saveVehiculoServicio(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();

        String vehiculoServicioString = request.getParameter("vehiculoServicio");
        VehiculoServicioDTO vehiculoServicioDTO = null;
        try {
            vehiculoServicioDTO = objectMapper.readValue(vehiculoServicioString, VehiculoServicioDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return vehiculoServicioService.saveVehiculoServicio(vehiculoServicioDTO, file);
    }

    @PostMapping("vehiculo/servicio/findAll")
    public List<VehiculoServicioDTO> getVehiculosServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getVehiculosServicios(filtroDTO);
    }

    @PostMapping("vehiculo/servicio/incidencia/servicios")
    public List<ServicioDTO> getIncidenciasServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getIncidenciasServicios(filtroDTO);
    }

    @PostMapping("vehiculo/servicio/costos")
    public List<CostoServiciosDTO> getCostoServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getCostoServicios(filtroDTO);
    }

    // VEHICULO CONSUMO
    @PostMapping("vehiculo/consumo")
    public VehiculoConsumoDTO saveVehiculoConsumo(@RequestBody VehiculoConsumoDTO vehiculoConsumoDTO) {
        return vehiculoConsumoService.saveVehiculoConsumo(vehiculoConsumoDTO);
    }

    @PostMapping("vehiculo/consumo/findAll")
    public List<VehiculoConsumoDTO> getVehiculosConsumos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getVehiculosConsumos(filtroDTO);
    }

    @PostMapping("vehiculo/consumo/incidencia/estaciones")
    public List<EstacionDTO> getIncidenciasEstaciones(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getIncidenciasEstaciones(filtroDTO);
    }

    @PostMapping("vehiculo/consumo/incidencia/productos")
    public List<ProductoDTO> getIncidenciasProductos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getIncidenciasProductos(filtroDTO);
    }

    @PostMapping("vehiculo/consumo/costos")
    public List<CostoConsumosDTO> getCostoConsumos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getCostoConsumos(filtroDTO);
    }

}
