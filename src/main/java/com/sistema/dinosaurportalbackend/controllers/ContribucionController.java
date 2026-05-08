package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.dto.ContribucionRequest;
import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import com.sistema.dinosaurportalbackend.logic.model.Contribucion;
import com.sistema.dinosaurportalbackend.logic.model.SesionUsuarioBean;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contribuciones")
public class ContribucionController {
    @Autowired private ModeloDatos modeloDatos;
    @Autowired private SesionUsuarioBean sesionUsuarioBean;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ContribucionRequest request) {
        if (!sesionUsuarioBean.isLogueado())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");

        Contribucion c = new Contribucion();
        c.setTitulo(request.getTitulo());
        c.setTipo(request.getTipo());
        c.setEpoca(request.getEpoca());
        c.setContenidoHtml(request.getContenidoHtml());

        Usuario usuario = new Usuario();
        usuario.setId(sesionUsuarioBean.getId());
        c.setUsuario(usuario);

        String error = modeloDatos.getContribucionService().crearContribucion(c);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución enviada para revisión");
    }

    @GetMapping("/mias")
    public ResponseEntity<?> mias() {
        if (!sesionUsuarioBean.isLogueado())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        return ResponseEntity.ok(modeloDatos.getContribucionService().findByUsuarioId(sesionUsuarioBean.getId()));
    }
}
