package com.lta.apis.controller;

import com.lta.apis.entity.Producto;
import com.lta.apis.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarProducto(@RequestBody Producto producto){
        Producto nuevoProducto = productoService.registrarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos (){
        List<Producto> productos = productoService.listarProducto();
        return ResponseEntity.ok(productos);
    }
}


