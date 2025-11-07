package com.lta.apis.service.impl;

import com.lta.apis.entity.EstadoProducto;
import com.lta.apis.entity.Producto;
import com.lta.apis.repository.ProductoRepository;
import com.lta.apis.service.ProductoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProducto(nombre);
    }

    @Override
    public Optional<Producto> buscarPorId(Long idProducto) {
        return productoRepository.findByIdProducto(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto productoExistente = productoRepository.findByIdProducto(idProducto).
                orElseThrow(() -> new Exception("Producto no encontrado"));

        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        return productoRepository.save(productoExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarProducto(Long idProducto) {
        productoRepository.findByIdProducto(idProducto).
                orElseThrow(() -> new Exception("Producto no encontrado"));

        productoRepository.deleteById(idProducto);
    }

    @Override
    @SneakyThrows
    public Producto cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto) {
        Producto productoExistente =  productoRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new Exception("Producto no encontrado"));

        productoExistente.setEstadoProducto(nuevoEstadoProducto);
        return productoRepository.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerProductoPorEstado(EstadoProducto estadoProducto) {
        return productoRepository.findByEstadoProducto(estadoProducto);
    }
}
