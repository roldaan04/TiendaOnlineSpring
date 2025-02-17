package org.example.tiendapractica.TiendaOnline.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotNull// deberia poder ser nula en la BD ya que tu compras o devuelves en el momento, por eso el LocalDate.now
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @ColumnDefault("1")
    @Column(name = "cantidad")
    private Integer cantidad;

    @Size(max = 100)
    @Pattern(regexp = "^(compra|devolucion)$", message = "el tipo solo puede ser o compra o devoluciom")
    @Column(name = "tipo", length = 100)
    private String tipo;

    @Size(max = 200)
    @Column(name = "descripcion", length = 200)
    private String descripcion;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = LocalDate.now();
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Agregar un getter para mostrar información básica del cliente
    @JsonProperty("cliente")
    public Map<String, Object> getClienteInfo() {
        Map<String, Object> clienteInfo = new HashMap<>();
        clienteInfo.put("id", cliente.getId()); //id obligatorio para la busqueda de spring de dicho cliente
        clienteInfo.put("nombre", cliente.getNombre());
        return clienteInfo;
    }

    @JsonProperty("producto")
    public Map<String, Object> getProductoInfo() {
        Map<String, Object> productoInfo = new HashMap<>();
        productoInfo.put("id", producto.getId());
        productoInfo.put("nombre", producto.getNombre());
        return productoInfo;
    }
}
