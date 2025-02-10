package org.example.tiendaonline.Controller;


import jakarta.validation.Valid;
import org.example.tiendaonline.DTO.Cliente;
import org.example.tiendaonline.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CacheConfig(cacheNames = {"clientes"})
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes= clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Optional<Cliente>> buscar(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Cliente> cliente = clienteService.getById(id);
            return ResponseEntity.ok(cliente);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<String> insertCliente(@Valid @RequestBody Cliente cliente) {
        Cliente cliente1= clienteService.insertCliente(cliente);
        if(cliente1!=null){
            return ResponseEntity.ok("Cliente insertado correctamente");
        }else{
            return ResponseEntity.badRequest().body("Cliente no insertado");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateCliente(@Valid @RequestBody Cliente cliente) {
        boolean actualizado= clienteService.updateCliente(cliente);
        if(actualizado){
            return ResponseEntity.ok("Cliente actualizado correctamente");
        }else{
            return ResponseEntity.badRequest().body("Cliente no actualizado");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
        boolean eliminado= clienteService.deleteCliente(id);
        if(eliminado){
            return ResponseEntity.ok("Cliente eliminado correctamente");
        }else{
            return ResponseEntity.badRequest().body("Cliente no encontrado");
        }
    }

}
