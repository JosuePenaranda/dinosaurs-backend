package com.sistema.dinosaurportalbackend.data;

import com.sistema.dinosaurportalbackend.logic.model.Contribucion;
import com.sistema.dinosaurportalbackend.logic.model.EstadoContribucion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContribucionRepository extends CrudRepository<Contribucion, Integer> {
    List<Contribucion> findByEstadoOrderByFechaCreacionAsc(EstadoContribucion estado);
    List<Contribucion> findByUsuario_IdOrderByFechaCreacionDesc(Integer usuarioId);
}
