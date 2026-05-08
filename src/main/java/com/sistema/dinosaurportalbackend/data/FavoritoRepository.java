package com.sistema.dinosaurportalbackend.data;

import com.sistema.dinosaurportalbackend.logic.model.Favorito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends CrudRepository<Favorito, Integer> {
    List<Favorito> findByUsuario_Id(Integer usuarioId);
    Optional<Favorito> findByUsuario_IdAndDinosaurio_Id(Integer usuarioId, Integer dinosaurioId);
    boolean existsByUsuario_IdAndDinosaurio_Id(Integer usuarioId, Integer dinosaurioId);
}
