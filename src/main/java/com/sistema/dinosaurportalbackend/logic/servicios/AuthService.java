package com.sistema.dinosaurportalbackend.logic.servicios;

import com.sistema.dinosaurportalbackend.dto.AuthRequest;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordHash passwordHash;
    @Autowired private JwtUtil jwtUtil;

    public Map<String, Object> login(AuthRequest request) {
        if (request.getUsername() == null || request.getPassword() == null)
            return Map.of("error", "Credenciales inválidas");

        Usuario usuario = usuarioService.findByUsername(request.getUsername());
        if (usuario == null)
            return Map.of("error", "Credenciales inválidas");
        if (!Boolean.TRUE.equals(usuario.getActivo()))
            return Map.of("error", "Usuario inactivo");
        if (!passwordHash.verify(request.getPassword(), usuario.getPassword()))
            return Map.of("error", "Credenciales inválidas");

        String token = jwtUtil.generateToken(usuario.getId(), usuario.getUsername(), usuario.getRol().name());
        return Map.of(
            "token",    token,
            "id",       usuario.getId(),
            "username", usuario.getUsername(),
            "rol",      usuario.getRol().name()
        );
    }
}
