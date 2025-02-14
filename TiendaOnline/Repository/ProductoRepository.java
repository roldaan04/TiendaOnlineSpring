package org.example.tiendaonline.TiendaOnline.Repository;

import org.example.tiendaonline.TiendaOnline.DTO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByNombre(String nombre);

    // Verificar si existe un producto con el mismo nombre y un ID diferente
    boolean existsByNombreAndIdNot(String nombre, Integer id);
}
