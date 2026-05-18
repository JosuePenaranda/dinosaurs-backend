package com.sistema.dinosaurportalbackend.logic;

import com.sistema.dinosaurportalbackend.logic.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Punto central de acceso a todos los servicios de la aplicación
@Component
public class ModeloDatos {
    @Autowired private AuthService authService;
    @Autowired private ContribucionService contribucionService;
    @Autowired private DinosaurioService dinosaurioService;
    @Autowired private FavoritoService favoritoService;
    @Autowired private UsuarioService usuarioService;

    // Devuelve el servicio de autenticación
    public AuthService getAuthService()             { return authService; }
    // Devuelve el servicio de contribuciones
    public ContribucionService getContribucionService() { return contribucionService; }
    // Devuelve el servicio de dinosaurios
    public DinosaurioService getDinosaurioService()   { return dinosaurioService; }
    // Devuelve el servicio de favoritos
    public FavoritoService getFavoritoService()       { return favoritoService; }
    // Devuelve el servicio de usuarios
    public UsuarioService getUsuarioService()         { return usuarioService; }
}
