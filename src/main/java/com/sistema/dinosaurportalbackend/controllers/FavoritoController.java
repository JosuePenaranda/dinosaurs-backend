package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import com.sistema.dinosaurportalbackend.logic.model.SesionUsuarioBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {
    @Autowired private ModeloDatos modeloDatos;
    @Autowired private SesionUsuarioBean sesionUsuarioBean;

    @GetMapping
    public ResponseEntity<?> listar() {
        if (!sesionUsuarioBean.isLogueado())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        return ResponseEntity.ok(modeloDatos.getFavoritoService().findByUsuarioId(sesionUsuarioBean.getId()));
    }

    @PostMapping("/{dinosaurioId}")
    public ResponseEntity<?> agregar(@PathVariable Integer dinosaurioId) {
        if (!sesionUsuarioBean.isLogueado())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        String error = modeloDatos.getFavoritoService().agregar(sesionUsuarioBean.getId(), dinosaurioId);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Agregado a favoritos");
    }

    @DeleteMapping("/{dinosaurioId}")
    public ResponseEntity<?> eliminar(@PathVariable Integer dinosaurioId) {
        if (!sesionUsuarioBean.isLogueado())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        String error = modeloDatos.getFavoritoService().eliminar(sesionUsuarioBean.getId(), dinosaurioId);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Eliminado de favoritos");
    }
}
