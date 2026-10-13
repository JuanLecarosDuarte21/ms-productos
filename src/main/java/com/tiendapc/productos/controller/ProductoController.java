
package com.tiendapc.productos.controller;

import com.tiendapc.productos.model.Producto;
import com.tiendapc.productos.service.ProductoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET: Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // GET: Buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear un producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(
            @Valid @RequestBody Producto producto) {

        Producto nuevoProducto = productoService.guardarProducto(producto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoProducto);
    }

    // PUT: Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody Producto producto) {

        return productoService.actualizarProducto(id, producto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        boolean eliminado = productoService.eliminarProducto(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
