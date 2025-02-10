package org.example.tiendaonline.Controller;


import jakarta.validation.Valid;
import org.example.tiendaonline.DTO.Compra;
import org.example.tiendaonline.Services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
@CacheConfig(cacheNames = {"compras"})
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
       List<Compra> compras= compraService.getAll();
       return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Compra>> getCompraById(@PathVariable Integer id) {
        Optional<Compra> compra = compraService.getById(id);
        return ResponseEntity.ok(compra);
    }

    @PostMapping
    public ResponseEntity<String> insertCompra(@Valid @RequestBody Compra compra) {
        Compra compra1= compraService.insertCompra(compra);
        if(compra1!=null){
                return ResponseEntity.ok().body("Compra insertada correctamente");
        }else{
            return ResponseEntity.badRequest().body("Compra no insertada");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCompra(@Valid @RequestBody Compra compra) {
        boolean actualizar= compraService.updateCompra(compra);
        if(actualizar){
            return ResponseEntity.ok().body("Compra actualizada correctamente");
        }else{
            return ResponseEntity.badRequest().body("Compra no actualizada");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCompra(@PathVariable Integer id) {
        boolean eliminar= compraService.deleteCompra(id);
        if(eliminar){
            return ResponseEntity.ok().body("Compra eliminada correctamente");
        }else{
            return ResponseEntity.badRequest().body("Compra no encontrada");
        }
    }

}
