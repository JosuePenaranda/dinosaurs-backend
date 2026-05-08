package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dinosaurios")
public class DinosaurioController {
    @Autowired private ModeloDatos modeloDatos;

    @GetMapping
    public ResponseEntity<?> listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String epoca) {
        return ResponseEntity.ok(modeloDatos.getDinosaurioService().buscar(nombre, tipo, epoca));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        var dinosaurio = modeloDatos.getDinosaurioService().findById(id);
        if (dinosaurio == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dinosaurio);
    }
}
