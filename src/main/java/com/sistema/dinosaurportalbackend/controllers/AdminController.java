package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.dto.DecisionRequest;
import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired private ModeloDatos modeloDatos;

    @GetMapping("/pendientes")
    public ResponseEntity<?> pendientes(HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("rol")))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        return ResponseEntity.ok(modeloDatos.getContribucionService().findPendientes());
    }

    @PostMapping("/contribuciones/{id}/aprobar")
    public ResponseEntity<?> aprobar(@PathVariable Integer id, @RequestBody DecisionRequest decision, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("rol")))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        String error = modeloDatos.getContribucionService().aprobar(id, decision.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución aprobada");
    }

    @PostMapping("/contribuciones/{id}/rechazar")
    public ResponseEntity<?> rechazar(@PathVariable Integer id, @RequestBody DecisionRequest decision, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("rol")))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
        String error = modeloDatos.getContribucionService().rechazar(id, decision.getObservacionAdmin());
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución rechazada");
    }
}
