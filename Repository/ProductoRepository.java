package org.example.tiendaonline.Repository;

import org.example.tiendaonline.DTO.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
