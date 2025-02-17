package org.example.tiendapractica.TiendaOnline.Services;

import org.example.tiendapractica.TiendaOnline.DTO.Cliente;
import org.example.tiendapractica.TiendaOnline.DTO.Historial;
import org.example.tiendapractica.TiendaOnline.DTO.Producto;
import org.example.tiendapractica.TiendaOnline.Repos.ClienteRepo;
import org.example.tiendapractica.TiendaOnline.Repos.HistorialRepo;
import org.example.tiendapractica.TiendaOnline.Repos.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistorialService {

    private final HistorialRepo historialRepo;
    private final ProductoRepo productoRepo;
    private final ClienteRepo clienteRepo;

    @Autowired
    public HistorialService (HistorialRepo historialRepo, ProductoRepo productoRepo, ClienteRepo clienteRepo) {
        this.historialRepo = historialRepo;
        this.productoRepo = productoRepo;
        this.clienteRepo = clienteRepo;
    }

    public List<Historial> findAll() {
        return historialRepo.findAll();
    }

    public Optional <Historial> findById(Integer id) {
        return historialRepo.findById(id);
    }

    public String realizarCompra(Historial historial) {
        Optional<Producto> producto = productoRepo.findById(historial.getProducto().getId());
        Optional<Cliente> cliente = clienteRepo.findById(historial.getCliente().getId());
        if(producto.isEmpty()) {
            return "Error: No existe el producto con ese id";
        }
        if(cliente.isEmpty()) {
            return "Error: No existe el cliente con ese id";
        }

        if(producto.get().getStock() < historial.getCantidad()) {
            return "Error: No hay suficiente stock para realizar la compra";
        }else{
            if(historial.getTipo().equals("compra")) {
                Producto p = producto.get();
                p.setStock(p.getStock() - historial.getCantidad());
                productoRepo.save(p);
                historial.setProducto(p);
                historialRepo.save(historial);
                return "Compra realizada con exito";
            }else{
                return "Error: No es posible realizar compra, el tipo deber ser compra";
            }
        }
    }

    public String realizarDevolucion(Historial historial) {
        Optional<Historial> compraPrevia = historialRepo.findById(historial.getId());
        Optional<Producto> producto = productoRepo.findById(historial.getProducto().getId());
        Optional<Cliente> cliente = clienteRepo.findById(historial.getCliente().getId());
        if(producto.isEmpty()) {
            return "Error: No existe el producto con ese id";
        }
        if(cliente.isEmpty()) {
            return "Error: No existe el cliente con ese id";
        }
        if(compraPrevia.isEmpty()) {
            return "Error: No existe ninguna compra con ese id";
        }
        if(compraPrevia.get().getFechaCompra().plusDays(30).isBefore(LocalDate.now())) {
            return "Error: No se puede devolver porque ya han pasado los 30 días de plazo";
        }else{
            Producto p = producto.get();
            p.setStock(p.getStock() + historial.getCantidad());
            productoRepo.save(p);
            historial.setProducto(p);
            historial.setFechaCompra(LocalDate.now());
            historial.setTipo("devolucion");
            historialRepo.save(historial);
            return "Devolucion realizada con exito";
        }
    }

    public boolean delete(Integer id) {
      Optional<Historial> historial = historialRepo.findById(id);
      if(historial.isPresent()) {
          historialRepo.delete(historial.get());
          return true;
      }else{
          return false;
      }
    }

}
