package org.example.tiendaonline.TiendaOnline.Servicios;

import org.example.tiendaonline.TiendaOnline.DTO.Cliente;
import org.example.tiendaonline.TiendaOnline.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
     @Autowired
     private ClienteRepository clienteRepository;

     public List<Cliente> findAll(){
         return clienteRepository.findAll();
     }

     public Optional<Cliente> findById(Integer id){
         return clienteRepository.findById(id);
     }

     public Cliente save(Cliente cliente){
         return clienteRepository.save(cliente);
     }

     public boolean update(Cliente cliente){
         Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
         if(optionalCliente.isPresent()){
             clienteRepository.save(cliente);
             return true;
         }else{
             return false;
         }
     }

     public boolean delete(Integer id){
         Optional<Cliente> optionalCliente = clienteRepository.findById(id);
         if(optionalCliente.isPresent()){
             clienteRepository.delete(optionalCliente.get());
             return true;
         }else{
             return false;
         }
     }
}
