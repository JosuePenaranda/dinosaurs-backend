package com.sistema.dragonballportalbackend.logic.servicios;

import com.sistema.dragonballportalbackend.dto.AuthRequest;
import com.sistema.dragonballportalbackend.logic.model.SesionUsuarioBean;
import com.sistema.dragonballportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordHash passwordHash;
    @Autowired private SesionUsuarioBean sesionUsuarioBean;

    public String login(AuthRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return "Credenciales inválidas";
        }

        Usuario usuario = usuarioService.findByUsername(request.getUsername());
        if (usuario == null) {
            return "Credenciales inválidas";
        }

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            return "Usuario inactivo";
        }

        if (!passwordHash.verify(request.getPassword(), usuario.getPassword())) {
            return "Credenciales inválidas";
        }

        sesionUsuarioBean.login(usuario.getId(), usuario.getUsername(), usuario.getRol(), true);
        return null;
    }

    public void logout() {
        sesionUsuarioBean.logout();
    }
}
