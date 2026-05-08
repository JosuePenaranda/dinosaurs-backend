package com.sistema.dinosaurportalbackend.logic;

import com.sistema.dinosaurportalbackend.logic.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModeloDatos {
    @Autowired private AuthService authService;
    @Autowired private ContribucionService contribucionService;
    @Autowired private DinosaurioService dinosaurioService;
    @Autowired private FavoritoService favoritoService;
    @Autowired private UsuarioService usuarioService;

    public AuthService getAuthService() { return authService; }
    public ContribucionService getContribucionService() { return contribucionService; }
    public DinosaurioService getDinosaurioService() { return dinosaurioService; }
    public FavoritoService getFavoritoService() { return favoritoService; }
    public UsuarioService getUsuarioService() { return usuarioService; }
}
