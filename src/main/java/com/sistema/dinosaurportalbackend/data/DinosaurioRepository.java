package com.sistema.dinosaurportalbackend.data;

import com.sistema.dinosaurportalbackend.logic.model.Dinosaurio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DinosaurioRepository extends CrudRepository<Dinosaurio, Integer> {
    List<Dinosaurio> findByPublicadoTrueOrderByNombreAsc();
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre);
    List<Dinosaurio> findByTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo);
    List<Dinosaurio> findByEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String epoca);
    List<Dinosaurio> findByTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo, String epoca);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String epoca);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo, String epoca);
}
