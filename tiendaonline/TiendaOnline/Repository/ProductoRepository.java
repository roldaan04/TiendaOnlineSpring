package org.example.tiendaonline.TiendaOnline.Repository;

import org.example.tiendaonline.TiendaOnline.DTO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
