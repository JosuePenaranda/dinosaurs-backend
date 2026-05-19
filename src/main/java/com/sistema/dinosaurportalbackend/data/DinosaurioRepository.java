package com.sistema.dinosaurportalbackend.data;

import com.sistema.dinosaurportalbackend.logic.model.Dinosaurio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repositorio para acceder a los dinosaurios en la base de datos
@Repository
public interface DinosaurioRepository extends CrudRepository<Dinosaurio, Integer> {
    List<Dinosaurio> findByPublicadoTrueOrderByNombreAsc();
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre);
    List<Dinosaurio> findByTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo);
    List<Dinosaurio> findByEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String epoca);
    List<Dinosaurio> findByCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String categoria);
    List<Dinosaurio> findByTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo, String epoca);
    List<Dinosaurio> findByTipoIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo, String categoria);
    List<Dinosaurio> findByEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String epoca, String categoria);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String epoca);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String categoria);
    List<Dinosaurio> findByTipoIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String tipo, String epoca, String categoria);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo, String epoca);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo, String categoria);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String epoca, String categoria);
    List<Dinosaurio> findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(String nombre, String tipo, String epoca, String categoria);
}
