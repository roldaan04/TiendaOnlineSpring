package org.example.tiendapractica.TiendaOnline.Repos;

import org.example.tiendapractica.TiendaOnline.DTO.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {
}
