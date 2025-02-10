package org.example.tiendaonline.TiendaOnline.Controladores;

import jakarta.validation.Valid;
import org.example.tiendaonline.TiendaOnline.DTO.Compra;
import org.example.tiendaonline.TiendaOnline.Servicios.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compra")
@CacheConfig(cacheNames = "{compra}")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> getCompras(){
        return ResponseEntity.ok(compraService.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Compra> getCompra(@PathVariable Integer id){
        try{
            Thread.sleep(3000);
            Optional<Compra> compra = compraService.findById(id);
            if(compra.isPresent()){
                return ResponseEntity.ok(compra.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody @Valid Compra compra){
        return ResponseEntity.ok(compraService.save(compra));
    }

    @PutMapping
    public ResponseEntity<String> updateCompra(@RequestBody @Valid Compra compra){
        boolean actualizado = compraService.update(compra);
        if(actualizado){
            return ResponseEntity.ok("Compra actualizado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no actualizada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable Integer id){
        boolean eliminado = compraService.delete(id);
        if(eliminado){
            return ResponseEntity.ok("Compra eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no eliminada");
        }
    }

}
