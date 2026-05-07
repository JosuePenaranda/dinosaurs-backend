package com.sistema.dragonballportalbackend.controllers;

import com.sistema.dragonballportalbackend.dto.AuthRequest;
import com.sistema.dragonballportalbackend.logic.ModeloDatos;
import com.sistema.dragonballportalbackend.logic.model.SesionUsuarioBean;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ModeloDatos modeloDatos;

    @Autowired
    private SesionUsuarioBean sesionUsuarioBean;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String error = modeloDatos.getAuthService().login(request);
        if (error != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        return ResponseEntity.ok(java.util.Map.of(
            "id", sesionUsuarioBean.getId(),
            "username", sesionUsuarioBean.getUsername(),
            "rol", sesionUsuarioBean.getRol().name()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        String error = modeloDatos.getUsuarioService().registrar(usuario);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        modeloDatos.getAuthService().logout();
        return ResponseEntity.ok("Sesión cerrada");
    }
}
