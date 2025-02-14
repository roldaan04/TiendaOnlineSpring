package org.example.tiendaonline.TiendaOnline.Controladores;

import jakarta.validation.Valid;
import org.example.tiendaonline.TiendaOnline.DTO.Producto;
import org.example.tiendaonline.TiendaOnline.Servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@CacheConfig(cacheNames = "{producto}")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @Cacheable
    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Integer id){
        try{
            Thread.sleep(3000);
            Optional<Producto> producto = productoService.findById(id);
            if(producto.isPresent()){
                return ResponseEntity.ok(producto.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Producto producto){
        try{
            Producto p = productoService.save(producto);
            return ResponseEntity.ok(p);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody @Valid Producto producto){
        try{
            boolean actualizado = productoService.update(producto);
            if(actualizado){
                return ResponseEntity.ok(producto.toString());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no ha sido actualizado");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean eliminado = productoService.delete(id);
        if(eliminado){
            return ResponseEntity.ok("El producto ha sido eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no ha sido eliminado");
        }
    }
}
