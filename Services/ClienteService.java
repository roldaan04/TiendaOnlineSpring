package org.example.tiendaonline.Services;

import org.example.tiendaonline.DTO.Cliente;
import org.example.tiendaonline.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getById(int id){
        return clienteRepository.findById(id);
    }

    public Cliente insertCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public boolean updateCliente(Cliente cliente){
        Optional<Cliente> cliente1= clienteRepository.findById(cliente.getId());
        if(cliente1.isPresent()){
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

    public boolean deleteCliente(int id){
        Optional<Cliente> cliente1= clienteRepository.findById(id);
        if(cliente1.isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
