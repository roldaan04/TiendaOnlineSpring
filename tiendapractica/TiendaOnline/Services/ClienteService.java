package org.example.tiendapractica.TiendaOnline.Services;

import org.example.tiendapractica.TiendaOnline.DTO.Cliente;
import org.example.tiendapractica.TiendaOnline.Repos.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepo clienteRepo;

    @Autowired
    public ClienteService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<Cliente> findAll() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> findById(Integer id) {
        return clienteRepo.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
       return clienteRepo.save(cliente);
    }

    public Boolean updateCliente(Cliente cliente) {
        Optional<Cliente> c =  clienteRepo.findById(cliente.getId());
        if (c.isPresent()) {
            clienteRepo.save(cliente);
            return true;

        }else{
            return false;
        }
    }

    public Boolean deleteCliente(Integer id) {
        Optional<Cliente> c =  clienteRepo.findById(id);
        if (c.isPresent()) {
            clienteRepo.delete(c.get());
            return true;
        }else{
            return false;
        }
    }
}
