package org.example.tiendaonline.TiendaOnline.Servicios;

import org.example.tiendaonline.TiendaOnline.DTO.Producto;
import org.example.tiendaonline.TiendaOnline.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer id){
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto){
        if(productoRepository.existsByNombre(producto.getNombre())){
            throw new IllegalArgumentException("El producto con este nombre ya existe.");
        }
        return productoRepository.save(producto);
    }

    public boolean update(Producto producto) {
        Optional<Producto> optional = productoRepository.findById(producto.getId());
        if (!optional.isPresent()) {
            return false;
        }

        // verificamos si el nombre ya está en uso por otro producto
        if (productoRepository.existsByNombreAndIdNot(producto.getNombre(), producto.getId())) {
            throw new IllegalArgumentException("El producto con este nombre ya existe.");
        }

        productoRepository.save(producto);
        return true;
    }


    public boolean delete(Integer id){
        Optional<Producto> optional = productoRepository.findById(id);
        if(optional.isPresent()){
            productoRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }
}
