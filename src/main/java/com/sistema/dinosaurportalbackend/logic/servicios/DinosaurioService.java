package com.sistema.dinosaurportalbackend.logic.servicios;

import com.sistema.dinosaurportalbackend.data.DinosaurioRepository;
import com.sistema.dinosaurportalbackend.logic.model.Dinosaurio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DinosaurioService {
    @Autowired private DinosaurioRepository dinosaurioRepository;

    public List<Dinosaurio> findAll() {
        List<Dinosaurio> lista = new ArrayList<>();
        dinosaurioRepository.findAll().forEach(lista::add);
        return lista;
    }

    public Dinosaurio findById(Integer id) {
        return dinosaurioRepository.findById(id).orElse(null);
    }


    public List<Dinosaurio> buscar(String nombre, String tipo, String epoca, String categoria) {
        boolean tieneNombre    = nombre    != null && !nombre.isBlank();
        boolean tieneTipo      = tipo      != null && !tipo.isBlank();
        boolean tieneEpoca     = epoca     != null && !epoca.isBlank();
        boolean tieneCategoria = categoria != null && !categoria.isBlank();

        if (tieneNombre && tieneTipo && tieneEpoca && tieneCategoria)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, tipo, epoca, categoria);
        if (tieneNombre && tieneTipo && tieneEpoca)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, tipo, epoca);
        if (tieneNombre && tieneTipo && tieneCategoria)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, tipo, categoria);
        if (tieneNombre && tieneEpoca && tieneCategoria)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, epoca, categoria);
        if (tieneTipo && tieneEpoca && tieneCategoria)
            return dinosaurioRepository.findByTipoIgnoreCaseAndEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(tipo, epoca, categoria);
        if (tieneNombre && tieneTipo)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, tipo);
        if (tieneNombre && tieneEpoca)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, epoca);
        if (tieneNombre && tieneCategoria)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre, categoria);
        if (tieneTipo && tieneEpoca)
            return dinosaurioRepository.findByTipoIgnoreCaseAndEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(tipo, epoca);
        if (tieneTipo && tieneCategoria)
            return dinosaurioRepository.findByTipoIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(tipo, categoria);
        if (tieneEpoca && tieneCategoria)
            return dinosaurioRepository.findByEpocaIgnoreCaseAndCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(epoca, categoria);
        if (tieneNombre)
            return dinosaurioRepository.findByNombreContainingIgnoreCaseAndPublicadoTrueOrderByNombreAsc(nombre);
        if (tieneTipo)
            return dinosaurioRepository.findByTipoIgnoreCaseAndPublicadoTrueOrderByNombreAsc(tipo);
        if (tieneEpoca)
            return dinosaurioRepository.findByEpocaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(epoca);
        if (tieneCategoria)
            return dinosaurioRepository.findByCategoriaIgnoreCaseAndPublicadoTrueOrderByNombreAsc(categoria);

        return dinosaurioRepository.findByPublicadoTrueOrderByNombreAsc();
    }

    public String guardar(Dinosaurio dinosaurio) {
        if (dinosaurio == null) return "El dinosaurio es nulo";
        if (dinosaurio.getNombre() == null || dinosaurio.getNombre().isBlank()) return "El nombre es requerido";
        if (dinosaurio.getTipo() == null || dinosaurio.getTipo().isBlank()) return "El tipo es requerido";
        if (dinosaurio.getEpoca() == null || dinosaurio.getEpoca().isBlank()) return "La época es requerida";
        if (dinosaurio.getPublicado() == null) dinosaurio.setPublicado(false);

        dinosaurioRepository.save(dinosaurio);
        return null;
    }
}
