
package com.tiendapc.productos.service;

import com.tiendapc.productos.model.Producto;
import com.tiendapc.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Buscar un producto por su ID
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Crear un nuevo producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Optional<Producto> actualizarProducto(
            Long id, Producto datosActualizados) {

        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(datosActualizados.getNombre());
            producto.setMarca(datosActualizados.getMarca());
            producto.setCategoria(datosActualizados.getCategoria());
            producto.setPrecio(datosActualizados.getPrecio());
            producto.setDescripcion(datosActualizados.getDescripcion());

            return productoRepository.save(producto);
        });
    }

    // Eliminar un producto
    public boolean eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);
        return true;
    }
}
