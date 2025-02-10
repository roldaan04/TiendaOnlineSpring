package org.example.tiendaonline.Services;

import org.example.tiendaonline.DTO.Compra;
import org.example.tiendaonline.Repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAll(){
        return compraRepository.findAll();
    }

    public Optional<Compra> getById(int id){
        return compraRepository.findById(id);
    }

    public Compra insertCompra(Compra compra){
        return compraRepository.save(compra);
    }

    public boolean updateCompra(Compra compra){
        Optional<Compra> compra1= compraRepository.findById(compra.getId());
        if (compra1.isPresent()){
            compraRepository.save(compra);
            return true;
        }
        return false;
    }

    public boolean deleteCompra(Integer id){
        Optional<Compra> compra1= compraRepository.findById(id);
        if (compra1.isPresent()){
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
