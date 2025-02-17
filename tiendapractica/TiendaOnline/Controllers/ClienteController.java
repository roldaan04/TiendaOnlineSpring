package org.example.tiendapractica.TiendaOnline.Controllers;

import jakarta.validation.Valid;
import org.example.tiendapractica.TiendaOnline.DTO.Cliente;
import org.example.tiendapractica.TiendaOnline.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CacheConfig(cacheNames = "{clientes}")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Integer id) {
        try{
            Thread.sleep(3000);
            Optional<Cliente> cliente = clienteService.findById(id);
            if(cliente.isPresent()){
                return ResponseEntity.ok(cliente.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @PutMapping
    public ResponseEntity<String> updateCliente(@RequestBody @Valid Cliente cliente) {
        boolean actualizado = clienteService.updateCliente(cliente);
        if(actualizado){
            return ResponseEntity.ok("Cliente actualizado");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente no actualizado, no existe cliente con ese id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
        boolean eliminado = clienteService.deleteCliente(id);
        if(eliminado){
            return ResponseEntity.ok("Cliente eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no eliminado, no existe cliente con ese id");
        }
    }
}
