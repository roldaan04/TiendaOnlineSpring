package org.example.tiendapractica.TiendaOnline.Services;

import org.example.tiendapractica.TiendaOnline.DTO.Producto;
import org.example.tiendapractica.TiendaOnline.Repos.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepo productoRepo;

    @Autowired
    public ProductoService(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    public List<Producto> findAll() {
        return productoRepo.findAll();
    }

    public Optional<Producto> findById(Integer id) {
        return productoRepo.findById(id);
    }

    public String crearProducto(Producto producto) {
        boolean productoExiste = productoRepo.existsByNombre(producto.getNombre());
        if(productoExiste) {
            return "Error: Ya existe un producto con ese nombre " + producto.getNombre();
        }else{
            productoRepo.save(producto);
            return "Producto creado correctamente";
        }
    }

    public String updateProducto(Producto producto) {
        Optional<Producto> productoOptional = productoRepo.findById(producto.getId());
        boolean productoExiste = productoRepo.existsByNombreAndIdNot(producto.getNombre(), producto.getId());
        if (productoOptional.isEmpty()) {
            return "Error: no existe un producto con ese id";
        }
        if(productoExiste){
            return "Error: ya existe un producto con ese nombre";
        }else{
            productoRepo.save(producto);
            return "Producto actualizado correctamente";
        }
    }


    public boolean deleteProducto(Integer id) {
        Optional<Producto> productoOptional = productoRepo.findById(id);
        if (productoOptional.isPresent()) {
            productoRepo.delete(productoOptional.get());
            return true;
        }else{
            return false;
        }
    }
}
