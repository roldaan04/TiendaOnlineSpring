package org.example.tiendapractica.TiendaOnline.Controllers;

import jakarta.validation.Valid;
import org.example.tiendapractica.TiendaOnline.DTO.Historial;
import org.example.tiendapractica.TiendaOnline.Services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historiales")
public class HistorialController {

    private final HistorialService historialService;

    @Autowired
    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping
    public ResponseEntity<List<Historial>> getHistoriales() {
        return ResponseEntity.ok(historialService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHistorial(@PathVariable Integer id) {
        Optional<Historial> historial = historialService.findById(id);
        if (historial.isPresent()) {
            return ResponseEntity.ok(historial.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado dicho historial");
        }
    }

    @PostMapping
    public ResponseEntity<?> createHistorial(@RequestBody @Valid Historial historial) {
        String mensaje = historialService.realizarCompra(historial);
        if(mensaje.startsWith("Error:")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }else{
            return ResponseEntity.ok(mensaje);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateHistorial(@RequestBody @Valid Historial historial) {
        String mensaje = historialService.realizarDevolucion(historial);
        if(mensaje.startsWith("Error:")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        }else{
            return ResponseEntity.ok(mensaje);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistorial(@PathVariable Integer id) {
        boolean ok = historialService.delete(id);
        if(ok){
            return ResponseEntity.ok("Ha borrado el historial");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado historial a borrar");
        }
    }
}
