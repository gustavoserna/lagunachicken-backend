package com.digitaul.lagunachicken.controller;

import com.digitaul.lagunachicken.domain.dto.*;
import com.digitaul.lagunachicken.domain.service.*;
import com.digitaul.lagunachicken.utility.ValidatorUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/*@CrossOrigin(
        origins = {"*", "http://demo.vidsaconstructora.com.mx"},
        maxAge = 3600,
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)*/
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
    @PreAuthorize("hasRole('ADMIN')")
    public ChoferDTO saveChofer(@RequestBody ChoferDTO choferDTO){
        return choferService.saveChofer(choferDTO);
    }

    @GetMapping("chofer/findAll")
    public List<ChoferDTO> getChoferes() {
        return choferService.getChoferes();
    }

    // ESTACION
    @PostMapping("estacion")
    @PreAuthorize("hasRole('ADMIN')")
    public EstacionDTO saveEstacion(@RequestBody EstacionDTO estacionDTO){
        return estacionService.saveEstacion(estacionDTO);
    }

    @GetMapping("estacion/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EstacionDTO> getEstaciones() {
        return estacionService.getEstaciones();
    }

    // PRODUCTO
    @PostMapping("producto")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductoDTO saveProducto(@RequestBody ProductoDTO productoDTO){
        return productoService.saveProducto(productoDTO);
    }

    @GetMapping("producto/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductoDTO> getProductos() {
        return productoService.getProductos();
    }

    // SUCURSAL
    @GetMapping("sucursal/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SucursalDTO> getSucursales() {
        return sucursalService.getSucursales();
    }

    // SERVICIO
    @PostMapping("servicio")
    @PreAuthorize("hasRole('ADMIN')")
    public ServicioDTO saveServicio(@RequestBody ServicioDTO servicioDTO) {
        return servicioService.saveServicio(servicioDTO);
    }

    @GetMapping("servicio/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ServicioDTO> getServicios() {
        return  servicioService.getServicios();
    }

    // PROVEEDOR
    @PostMapping("proveedor")
    @PreAuthorize("hasRole('ADMIN')")
    public ProveedorDTO saveProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return proveedorService.saveProveedor(proveedorDTO);
    }

    @GetMapping("proveedor/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProveedorDTO> getProveedores() {
        return  proveedorService.getProveedores();
    }

    // VEHICULO
    @PostMapping("vehiculo")
    @PreAuthorize("hasRole('ADMIN')")
    public VehiculoDTO saveVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        return vehiculoService.saveVehiculo(vehiculoDTO);
    }

    @GetMapping("vehiculo/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<VehiculoDTO> getVehiculos() {
        return vehiculoService.getVehiculos();
    }

    @PostMapping("vehiculo/servicio")
    @PreAuthorize("hasRole('ADMIN')")
    public VehiculoServicioDTO saveVehiculoServicio(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();

        String vehiculoServicioString = request.getParameter("vehiculoServicio");
        VehiculoServicioDTO vehiculoServicioDTO = null;
        try {
            vehiculoServicioDTO = objectMapper.readValue(vehiculoServicioString, VehiculoServicioDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return file == null ? vehiculoServicioService.saveVehiculoServicio(vehiculoServicioDTO) : vehiculoServicioService.saveVehiculoServicio(vehiculoServicioDTO, file);
    }

    @PostMapping("vehiculo/servicio/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<VehiculoServicioDTO> getVehiculosServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getVehiculosServicios(filtroDTO);
    }

    @PostMapping("vehiculo/servicio/incidencia/servicios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ServicioDTO> getIncidenciasServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getIncidenciasServicios(filtroDTO);
    }

    @PostMapping("vehiculo/servicio/costos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CostoServiciosDTO> getCostoServicios(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoServicioService.getCostoServicios(filtroDTO);
    }

    // VEHICULO CONSUMO
    @PostMapping("vehiculo/consumo")
    @PreAuthorize("hasRole('ADMIN')")
    public VehiculoConsumoDTO saveVehiculoConsumo(@RequestBody VehiculoConsumoDTO vehiculoConsumoDTO, @RequestParam("modify") boolean modify) {
        Set<ConstraintViolation<VehiculoConsumoDTO>> violations = ValidatorUtil.validarCampos(vehiculoConsumoDTO);

        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<VehiculoConsumoDTO> violation : violations) {
                errorMessage.append("Error en campo ")
                        .append(violation.getPropertyPath())
                        .append(": ")
                        .append(violation.getMessage())
                        .append(". ");
            }
            throw new NullPointerException((errorMessage.toString()));
        }

        return vehiculoConsumoService.saveVehiculoConsumo(vehiculoConsumoDTO, modify);
    }

    @PostMapping("vehiculo/consumo/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<VehiculoConsumoDTO> getVehiculosConsumos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getVehiculosConsumos(filtroDTO);
    }

    @PostMapping("vehiculo/consumo/incidencia/productos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductoDTO> getIncidenciasProductos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getIncidenciasProductos(filtroDTO);
    }

    @PostMapping("vehiculo/consumo/costos")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CostoConsumosDTO> getCostoConsumos(@RequestBody FiltroDTO filtroDTO) {
        return vehiculoConsumoService.getCostoConsumos(filtroDTO);
    }

}
