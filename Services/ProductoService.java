package org.example.tiendaonline.Services;

import org.example.tiendaonline.DTO.Producto;
import org.example.tiendaonline.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto insertProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public boolean updateProducto(Producto producto) {
        Optional<Producto> nuevoProducto = productoRepository.findById(producto.getId());
        if(nuevoProducto.isPresent()) {
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    public boolean deleteProducto(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
