package org.example.tiendaonline.TiendaOnline.Controladores;

import jakarta.validation.Valid;
import org.example.tiendaonline.TiendaOnline.DTO.Historial;
import org.example.tiendaonline.TiendaOnline.Servicios.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historial")
@CacheConfig(cacheNames = "{historial}")
public class HistorialController {
    @Autowired
    private HistorialService historialService;

    @GetMapping
    public ResponseEntity<List<Historial>> getCompras(){
        return ResponseEntity.ok(historialService.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Historial> getCompra(@PathVariable Integer id){
        try{
            Thread.sleep(3000);
            Optional<Historial> historial = historialService.findById(id);
            if(historial.isPresent()){
                return ResponseEntity.ok(historial.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createHistorial(@RequestBody @Valid Historial historial){
            boolean insertado = historialService.save(historial);
            if(insertado){
                return ResponseEntity.ok("Historial guardado");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
    }

    @PutMapping
    public ResponseEntity<?> realizarDevolucion(@RequestBody @Valid Historial devolucion){
        boolean actualizado = historialService.update(devolucion);
        if(actualizado){
            return ResponseEntity.ok("Historial actualizado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Historial no actualizada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable Integer id){
        boolean eliminado = historialService.delete(id);
        if(eliminado){
            return ResponseEntity.ok("Historial eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Historial no eliminada");
        }
    }

}
