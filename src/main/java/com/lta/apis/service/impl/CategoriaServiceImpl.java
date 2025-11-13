package com.lta.apis.service.impl;

import com.lta.apis.entity.Categoria;
import com.lta.apis.exceptions.BadRequestException;
import com.lta.apis.exceptions.ResourseNotFoundException;
import com.lta.apis.repository.CategoriaRepository;
import com.lta.apis.service.CategoriaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

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
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourseNotFoundException("Categoria no encontrada"));

        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());

        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public void eliminarCategoria(Long idCategoria) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(idCategoria);

        if (!categoriaExistente.isPresent()){
            throw new ResourseNotFoundException("Categoria no encontrada para eliminar");
        }

        categoriaRepository.deleteById(idCategoria);

    }
}
