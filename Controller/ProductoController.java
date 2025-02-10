package org.example.tiendaonline.Controller;

import jakarta.validation.Valid;
import org.example.tiendaonline.DTO.Producto;
import org.example.tiendaonline.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CacheConfig(cacheNames = {"productos"})
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @GetMapping
    public ResponseEntity<List<Producto>> getAll(){
        List<Producto> productos= productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Producto>> getById(@PathVariable Integer id){
        try {
            Thread.sleep(3000);
            Optional<Producto> producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        }catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<String> insertProducto(@Valid @RequestBody Producto producto){
        Producto producto1= productoService.insertProducto(producto);
        if(producto1!=null){
            return ResponseEntity.ok("Producto insertado correctado");
        }else{
            return ResponseEntity.badRequest().body("Error al insertar producto");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateProducto(@Valid @RequestBody Producto producto){
        boolean actualizado= productoService.updateProducto(producto);
        if(actualizado){
            return ResponseEntity.ok("Producto modificado correctamente");
        }else{
            return ResponseEntity.badRequest().body("Error al modificar producto");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducto(@PathVariable Integer id){
        boolean eliminado= productoService.deleteProducto(id);
        if(eliminado){
            return ResponseEntity.ok("Producto eliminado correctamente");
        }else{
            return ResponseEntity.badRequest().body("Error al eliminar producto");
        }
    }


}
