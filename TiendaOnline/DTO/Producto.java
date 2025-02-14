package org.example.tiendaonline.TiendaOnline.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @NotBlank(message = "el campo nombre es obligatorio")
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "el campo nombre solo puede contener caracteres alfanuméricos")
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @Lob
    @NotBlank(message = "el campo descripcion es obligatorio")
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "el campo precio es obligatorio")
    @DecimalMin(value = "0.00", inclusive = false, message = "el precio debe ser un valor mayor a 0") //para establecer el precio de los productos mayor a 0
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "el campo stock es obligatorio" ) // para integer no se puede usar notblank
    @ColumnDefault("0")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        if(precio.compareTo(BigDecimal.TEN) < 0){
            setDescripcion(this.descripcion + "|| producto de oferta");
        } else if (precio.compareTo(new BigDecimal("200")) > 0) {
            setDescripcion(this.descripcion + "|| producto de calidad");
        }
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}