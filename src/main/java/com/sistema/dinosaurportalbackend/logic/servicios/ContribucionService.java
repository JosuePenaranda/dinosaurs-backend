package com.sistema.dinosaurportalbackend.logic.servicios;

import com.sistema.dinosaurportalbackend.data.ContribucionRepository;
import com.sistema.dinosaurportalbackend.logic.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContribucionService {
    @Autowired private ContribucionRepository contribucionRepository;
    @Autowired private UsuarioService usuarioService;
    @Autowired private DinosaurioService dinosaurioService;

    public List<Contribucion> findPendientes() {
        return contribucionRepository.findByEstadoOrderByFechaCreacionAsc(EstadoContribucion.PENDIENTE);
    }

    public List<Contribucion> findByUsuarioId(Integer usuarioId) {
        return contribucionRepository.findByUsuario_IdOrderByFechaCreacionDesc(usuarioId);
    }

    public Contribucion findById(Integer id) {
        return contribucionRepository.findById(id).orElse(null);
    }

    public String crearContribucion(Contribucion contribucion) {
        if (contribucion == null) return "La contribución es nula";
        if (contribucion.getTitulo() == null || contribucion.getTitulo().isBlank()) return "El título es requerido";
        if (contribucion.getTipo() == null || contribucion.getTipo().isBlank()) return "El tipo es requerido";
        if (contribucion.getEpoca() == null || contribucion.getEpoca().isBlank()) return "La época es requerida";
        if (contribucion.getContenidoHtml() == null || contribucion.getContenidoHtml().isBlank()) return "El contenido es requerido";
        if (contribucion.getUsuario() == null || contribucion.getUsuario().getId() == null) return "El usuario es requerido";

        Usuario usuario = usuarioService.findById(contribucion.getUsuario().getId());
        if (usuario == null) return "El usuario no existe";

        contribucion.setUsuario(usuario);
        contribucion.setEstado(EstadoContribucion.PENDIENTE);
        contribucion.setFechaCreacion(Instant.now());
        contribucionRepository.save(contribucion);
        return null;
    }

    public String aprobar(Integer id, String observacionAdmin) {
        Contribucion contribucion = findById(id);
        if (contribucion == null) return "La contribución no existe";
        if (contribucion.getEstado() != EstadoContribucion.PENDIENTE) return "La contribución ya fue procesada";

        Dinosaurio dinosaurio = new Dinosaurio();
        dinosaurio.setNombre(contribucion.getTitulo());
        dinosaurio.setTipo(contribucion.getTipo());
        dinosaurio.setEpoca(contribucion.getEpoca());
        dinosaurio.setDescripcion(contribucion.getContenidoHtml());
        dinosaurio.setPublicado(true);
        dinosaurio.setAutor(contribucion.getUsuario());
        dinosaurioService.guardar(dinosaurio);

        contribucion.setEstado(EstadoContribucion.APROBADA);
        contribucion.setObservacionAdmin(observacionAdmin);
        contribucionRepository.save(contribucion);
        return null;
    }

    public String rechazar(Integer id, String observacionAdmin) {
        Contribucion contribucion = findById(id);
        if (contribucion == null) return "La contribución no existe";
        if (contribucion.getEstado() != EstadoContribucion.PENDIENTE) return "La contribución ya fue procesada";

        contribucion.setEstado(EstadoContribucion.RECHAZADA);
        contribucion.setObservacionAdmin(observacionAdmin);
        contribucionRepository.save(contribucion);
        return null;
    }
}
