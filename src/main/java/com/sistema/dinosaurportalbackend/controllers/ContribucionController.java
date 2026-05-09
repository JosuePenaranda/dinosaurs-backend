package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.dto.ContribucionRequest;
import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import com.sistema.dinosaurportalbackend.logic.model.Contribucion;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/contribuciones")
public class ContribucionController {
    @Autowired private ModeloDatos modeloDatos;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ContribucionRequest request, HttpServletRequest httpRequest) {
        Integer userId = (Integer) httpRequest.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");

        Contribucion c = new Contribucion();
        c.setTitulo(request.getTitulo());
        c.setTipo(request.getTipo());
        c.setEpoca(request.getEpoca());
        c.setCategoria(request.getCategoria());
        c.setHabitat(request.getHabitat());
        c.setAlimentacion(request.getAlimentacion());
        c.setTamanio(request.getTamanio());
        c.setCuriosidades(request.getCuriosidades());
        c.setImagen(request.getImagen());
        c.setContenidoHtml(request.getContenidoHtml());

        Usuario usuario = new Usuario();
        usuario.setId(userId);
        c.setUsuario(usuario);

        String error = modeloDatos.getContribucionService().crearContribucion(c);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Contribución enviada para revisión");
    }

    @GetMapping("/mias")
    public ResponseEntity<?> mias(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");
        return ResponseEntity.ok(modeloDatos.getContribucionService().findByUsuarioId(userId));
    }

    @PostMapping("/imagen")
    public ResponseEntity<?> subirImagen(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autenticado");

        String nombre = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path destino = Paths.get("../Dinosaur-frontend/public/images/dinosaurios/" + nombre);

        try {
            Files.createDirectories(destino.getParent());
            file.transferTo(destino.toFile());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar imagen");
        }

        return ResponseEntity.ok("/images/dinosaurios/" + nombre);
    }
}
