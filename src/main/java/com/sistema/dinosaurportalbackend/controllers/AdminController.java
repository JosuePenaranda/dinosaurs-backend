package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.dto.DecisionRequest;
import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import com.sistema.dinosaurportalbackend.logic.model.SesionUsuarioBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired private ModeloDatos modeloDatos;
    @Autowired private SesionUsuarioBean sesionUsuarioBean;

    @GetMapping("/pendientes")
    public ResponseEntity<?> pendientes() {
        if (!sesionUsuarioBean.isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        return ResponseEntity.ok(modeloDatos.getContribucionService().findPendientes());
    }

    @PostMapping("/contribuciones/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Integer id, @RequestBody DecisionRequest request) {
        if (!sesionUsuarioBean.isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        String error = modeloDatos.getContribucionService().aprobar(id, request.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución aprobada");
    }

    @PostMapping("/contribuciones/{id}/rechazar")
    public ResponseEntity<?> rechazar(@PathVariable Integer id, @RequestBody DecisionRequest request) {
        if (!sesionUsuarioBean.isAdmin())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        String error = modeloDatos.getContribucionService().rechazar(id, request.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución rechazada");
    }
}
