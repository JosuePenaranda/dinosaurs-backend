package com.sistema.dinosaurportalbackend.controllers;

import com.sistema.dinosaurportalbackend.dto.AuthRequest;
import com.sistema.dinosaurportalbackend.logic.ModeloDatos;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private ModeloDatos modeloDatos;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Map<String, Object> result = modeloDatos.getAuthService().login(request);
        if (result.containsKey("error"))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.get("error"));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        String error = modeloDatos.getUsuarioService().registrar(usuario);
        if (error != null) return ResponseEntity.badRequest().body(error);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
