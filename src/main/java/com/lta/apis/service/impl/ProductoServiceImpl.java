package com.lta.apis.service.impl;

import com.lta.apis.entity.Categoria;
import com.lta.apis.entity.EstadoProducto;
import com.lta.apis.entity.Producto;
import com.lta.apis.exceptions.ResourseNotFoundException;
import com.lta.apis.repository.CategoriaRepository;
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
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Producto registrarProducto(Long categoriaId, Producto producto) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourseNotFoundException("No se encontró la categoria"));

        producto.setCategoria(categoria);

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
    public Producto actualizarProducto(Long idProducto, Producto producto) {
        Producto productoExistente = productoRepository.findByIdProducto(idProducto).
                orElseThrow(() -> new ResourseNotFoundException("Producto no encontrado"));

        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setDescripcionProducto(producto.getDescripcionProducto());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        productoExistente.setEstadoProducto(producto.getEstadoProducto());

        if(producto.getCategoria() != null && producto.getCategoria().getIdCategoria() != null ){
            Categoria categoria = categoriaRepository.findById(producto.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new ResourseNotFoundException("No se encontró la categoria"));
            productoExistente.setCategoria(categoria);
        }

        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepository.findByIdProducto(idProducto).
                orElseThrow(() -> new ResourseNotFoundException("Producto no encontrado"));

        productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto) {
        Producto productoExistente =  productoRepository.findByIdProducto(idProducto)
                .orElseThrow(() -> new ResourseNotFoundException("Producto no encontrado"));

        productoExistente.setEstadoProducto(nuevoEstadoProducto);
        return productoRepository.save(productoExistente);
    }

    @Override
    public List<Producto> obtenerProductoPorEstado(EstadoProducto estadoProducto) {
        return productoRepository.findByEstadoProducto(estadoProducto);
    }
}
