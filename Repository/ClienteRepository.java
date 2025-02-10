package org.example.tiendaonline.Repository;

import org.example.tiendaonline.DTO.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
