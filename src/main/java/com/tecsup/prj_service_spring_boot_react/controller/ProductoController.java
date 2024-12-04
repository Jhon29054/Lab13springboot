package com.tecsup.prj_service_spring_boot_react.controller;

import com.tecsup.prj_service_spring_boot_react.exception.ResourceNotFoundException;
import com.tecsup.prj_service_spring_boot_react.model.Producto;
import com.tecsup.prj_service_spring_boot_react.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> listarProductoPorId(@PathVariable long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable long id, @RequestBody Producto productoRequest) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));

        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setCantidad(productoRequest.getCantidad());
        producto.setCategoria(productoRequest.getCategoria());
        producto.setImagenUrl(productoRequest.getImagenUrl());

        Producto productoActualizado = productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el ID: " + id));

        productoRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
