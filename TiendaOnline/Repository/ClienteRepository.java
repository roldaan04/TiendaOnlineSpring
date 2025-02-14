package org.example.tiendaonline.TiendaOnline.Repository;

import org.example.tiendaonline.TiendaOnline.DTO.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
