package com.lta.apis.controller;

import com.lta.apis.entity.EstadoProducto;
import com.lta.apis.entity.Producto;
import com.lta.apis.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre){
        Optional<Producto> producto = productoService.buscarPorNombre(nombre);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
    }

    @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<?> buscarIdProducto(@PathVariable Long idProducto){
        Optional<Producto> producto = productoService.buscarPorId(idProducto);
        return producto.isPresent() ? ResponseEntity.ok(producto.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
    }

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long idProducto, @RequestBody Producto producto){
        try {
            Producto productoActualizado = new Producto();
            productoActualizado.setNombreProducto(producto.getNombreProducto());
            productoActualizado.setDescripcionProducto(producto.getDescripcionProducto());
            productoActualizado.setPrecio(producto.getPrecio());
            productoActualizado.setCantidad(producto.getCantidad());
            productoActualizado.setEstadoProducto(producto.getEstadoProducto());

            Producto productoBBDD = productoService.actualizarProducto(idProducto, productoActualizado);
            return ResponseEntity.ok(productoActualizado);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }


    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto){
        try{
            productoService.eliminarProducto(idProducto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @PutMapping("estado/{idProducto}")
    public ResponseEntity<?> cambiarEstadoProducto(@PathVariable Long idProducto, @RequestBody EstadoProducto estadoProducto){
        try{
            Producto producto = productoService.cambiarEstadoProducto(idProducto, estadoProducto);
            producto.setEstadoProducto(estadoProducto);

            return ResponseEntity.ok(producto);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encotrado");
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Producto>> listarProductoPorEstado(@PathVariable EstadoProducto estado){
        List<Producto> productos = productoService.obtenerProductoPorEstado(estado);

        return ResponseEntity.ok(productos);
    }
}


