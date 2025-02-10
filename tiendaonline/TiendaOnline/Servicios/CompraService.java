package org.example.tiendaonline.TiendaOnline.Servicios;

import org.example.tiendaonline.TiendaOnline.DTO.Compra;
import org.example.tiendaonline.TiendaOnline.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> findAll(){
        return compraRepository.findAll();
    }

    public Optional<Compra> findById(Integer id){
        return compraRepository.findById(id);
    }

    public Compra save(Compra compra){
        return compraRepository.save(compra);
    }

    public boolean update(Compra compra){
        Optional<Compra> optional = compraRepository.findById(compra.getId());
        if(optional.isPresent()){
            compraRepository.save(compra);
            return true;
        }else{
            return false;
        }
    }

    public boolean delete(Integer id){
        Optional<Compra> optional = compraRepository.findById(id);
        if(optional.isPresent()){
            compraRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }
}
