package org.example.tiendapractica.TiendaOnline.Controllers;

import jakarta.validation.Valid;
import org.example.tiendapractica.TiendaOnline.DTO.Producto;
import org.example.tiendapractica.TiendaOnline.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Integer id) {
       Optional<Producto> producto = productoService.findById(id);
       if (producto.isPresent()) {
           return ResponseEntity.ok(producto.get());
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
       }
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody @Valid Producto producto) {
        String mensaje = productoService.crearProducto(producto);
        if (mensaje.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }else{
            return ResponseEntity.ok(mensaje);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProducto(@RequestBody @Valid Producto producto) {
        String mensaje = productoService.updateProducto(producto);
        if (mensaje.startsWith("Error: ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }else{
            return ResponseEntity.ok(mensaje);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Producto producto) {
        boolean eliminado = productoService.deleteProducto(producto.getId());
        if (eliminado) {
            return ResponseEntity.ok("El producto ha sido eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el producto");
        }
    }
}
