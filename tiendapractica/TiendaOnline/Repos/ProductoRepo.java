package org.example.tiendapractica.TiendaOnline.Repos;

import org.example.tiendapractica.TiendaOnline.DTO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Integer id);
}
