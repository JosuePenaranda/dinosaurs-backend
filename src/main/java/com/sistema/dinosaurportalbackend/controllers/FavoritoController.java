package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Controlador para gestionar los favoritos del usuario autenticado
@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {
    @Autowired private ModeloDatos modeloDatos;

    // Devuelve todos los favoritos del usuario autenticado
    @GetMapping
    public ResponseEntity<?> listar(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        return ResponseEntity.ok(modeloDatos.getFavoritoService().findByUsuarioId(userId));
    }

    // Agrega un dinosaurio a los favoritos del usuario autenticado
    @PostMapping("/{dinosaurioId}")
    public ResponseEntity<?> agregar(@PathVariable Integer dinosaurioId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        String error = modeloDatos.getFavoritoService().agregar(userId, dinosaurioId);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Agregado a favoritos");
    }

    // Elimina un dinosaurio de los favoritos del usuario autenticado
    @DeleteMapping("/{dinosaurioId}")
    public ResponseEntity<?> eliminar(@PathVariable Integer dinosaurioId, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        String error = modeloDatos.getFavoritoService().eliminar(userId, dinosaurioId);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Eliminado de favoritos");
    }
}
