package org.example.tiendaonline.TiendaOnline.Controladores;

import jakarta.validation.Valid;
import org.example.tiendaonline.TiendaOnline.DTO.Cliente;
import org.example.tiendaonline.TiendaOnline.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CacheConfig(cacheNames = "{cliente}")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        try{
            Thread.sleep(3000);
            Optional<Cliente> cliente = clienteService.findById(id);
            if(cliente.isPresent()){
                return ResponseEntity.ok(cliente.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody @Valid Cliente cliente){
        boolean actualizado = clienteService.update(cliente);
        if(actualizado){
            return ResponseEntity.ok("Cliente actualizado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no actualizado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean eliminado = clienteService.delete(id);
        if(eliminado){
            return ResponseEntity.ok("Cliente eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no eliminado");
        }
    }
}
