package com.digitaul.lagunachicken.domain.service;

import com.digitaul.lagunachicken.domain.dto.ProductoDTO;
import com.digitaul.lagunachicken.domain.dto.ProveedorDTO;
import com.digitaul.lagunachicken.persistence.crud.IProductoRepository;
import com.digitaul.lagunachicken.persistence.entity.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        return convertToDTO(productoRepository.save(convertToEntity(productoDTO)));
    }

    public ProductoDTO getById(int idProducto) {
        Producto producto = productoRepository.findByIdProducto(idProducto);

        return convertToDTO(producto);
    }

    public List<ProductoDTO> getProductos() {
        List<ProductoDTO> productoDTOS = new ArrayList<>();
        List<Producto> productos = productoRepository.findAll();

        for (Producto producto : productos) {
            productoDTOS.add(convertToDTO(producto));
        }

        return productoDTOS;
    }

    private ProductoDTO convertToDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }

    private Producto convertToEntity(ProductoDTO productoDTO) {
        return modelMapper.map(productoDTO, Producto.class);
    }

}
