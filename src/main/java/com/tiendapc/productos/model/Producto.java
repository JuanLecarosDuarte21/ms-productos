
package com.tiendapc.productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precio;

    private String descripcion;

    // Relacion: un producto puede tener varias imagenes
    @OneToMany(
        mappedBy = "producto",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ImagenProducto> imagenes = new ArrayList<>();

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Obtener las imagenes del producto
    public List<ImagenProducto> getImagenes() {
        return imagenes;
    }

    // Asignar las imagenes del producto
    public void setImagenes(List<ImagenProducto> nuevasImagenes) {
        for (ImagenProducto imagen : imagenes) {
            imagen.setProducto(null);
        }

        imagenes.clear();

        if (nuevasImagenes != null) {
            for (ImagenProducto imagen : new ArrayList<>(nuevasImagenes)) {
                agregarImagen(imagen);
            }
        }
    }

    // Agregar una imagen y asociarla al producto
    public void agregarImagen(ImagenProducto imagen) {
        imagenes.add(imagen);
        imagen.setProducto(this);
    }
}
