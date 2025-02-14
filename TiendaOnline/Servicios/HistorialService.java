package org.example.tiendaonline.TiendaOnline.Servicios;

import org.example.tiendaonline.TiendaOnline.DTO.Historial;
import org.example.tiendaonline.TiendaOnline.DTO.Producto;
import org.example.tiendaonline.TiendaOnline.Repository.HistorialRepository;
import org.example.tiendaonline.TiendaOnline.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {
    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Historial> findAll(){
        return historialRepository.findAll();
    }

    public Optional<Historial> findById(Integer id){
        return historialRepository.findById(id);
    }

    public boolean save(Historial historial){
        Optional <Producto> producto = productoRepository.findById(historial.getProducto().getId());
        if(producto.isPresent()){
                Producto p = producto.get();
                if(historial.getTipo().equals("compra")){
                    if(historial.getCantidad() > p.getStock()){
                        return false;
                    }else{
                        p.setStock(p.getStock() - historial.getCantidad());
                        historial.setProducto(p);
                        productoRepository.save(p);
                        historialRepository.save(historial);
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean update(Historial devolucion){
        Optional <Historial> compraPrevia = historialRepository.findById(devolucion.getId());
        if(compraPrevia.isPresent()){
            if(devolucion.getFechaCompra().plusDays(30).isBefore(LocalDate.now())){
                return false;
            }else{
                Historial d = compraPrevia.get();
                Producto p = compraPrevia.get().getProducto();
                p.setStock(p.getStock() + devolucion.getCantidad());
                productoRepository.save(p);

                d.setTipo(devolucion.getTipo());
                d.setDescripcion(devolucion.getDescripcion());

                historialRepository.save(d);
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean delete(Integer id){
        Optional<Historial> optional = historialRepository.findById(id);
        if(optional.isPresent()){
            historialRepository.delete(optional.get());
            return true;
        }else{
            return false;
        }
    }

}
