package org.example.tiendapractica.TiendaOnline.Repos;

import org.example.tiendapractica.TiendaOnline.DTO.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistorialRepo extends JpaRepository<Historial, Integer> {
}
