package com.lta.apis.controller;

import com.lta.apis.entity.Categoria;
import com.lta.apis.service.CategoriaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/registrar")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);

        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    };

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = categoriaService.listarCategorias();

        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/buscar/id/{idCategoria}")
    @SneakyThrows
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long idCategoria){
        Optional<Categoria> categoriaOptional = categoriaService.obtenerCategoriaPorId(idCategoria);

        if (categoriaOptional.isPresent()){
            return new ResponseEntity<>(categoriaOptional.get(), HttpStatus.OK);
        }else{
            throw new Exception("Categoria no encontrada");
        }
    }

    @PutMapping("/actualizar/{idCategoria}")
    @SneakyThrows
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria, @RequestBody Categoria categoria){
        try {
            Categoria categoriaActualizada = categoriaService.actualizarCategoria(idCategoria, categoria);
            if (categoriaActualizada != null){
                return new ResponseEntity<>(categoriaActualizada, HttpStatus.OK);
            }else {
                throw new Exception("Categoria no encontrada");
            }
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{idCategoria}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long idCategoria){
        try {
            categoriaService.eliminarCategoria(idCategoria);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
