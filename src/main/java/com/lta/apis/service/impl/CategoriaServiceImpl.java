package com.lta.apis.service.impl;

import com.lta.apis.entity.Categoria;
import com.lta.apis.repository.CategoriaRepository;
import com.lta.apis.service.CategoriaService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @SneakyThrows
    @Override
    public Categoria crearCategoria(Categoria categoria) {
        if(categoriaRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
            throw new BadRequestException("Ya existe una categoria con ese nombre");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long idCategoria) {
        return categoriaRepository.findById(idCategoria);
    }

    @Override
    @SneakyThrows
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new Exception("Categoria no encontrada"));

        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());

        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    @SneakyThrows
    public void eliminarCategoria(Long idCategoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);

        if (!categoriaExistente.isPresent()){
            throw new Exception("Categoria no encontrada para eliminar");
        }

        categoriaRepository.deleteById(idCategoria);

    }
}
