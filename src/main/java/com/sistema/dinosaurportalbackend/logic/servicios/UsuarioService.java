package com.sistema.dinosaurportalbackend.logic.servicios;

import com.sistema.dinosaurportalbackend.data.UsuarioRepository;
import com.sistema.dinosaurportalbackend.logic.model.Rol;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Maneja la lógica de los usuarios
@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordHash passwordHash;

    // Devuelve todos los usuarios
    public List<Usuario> findAll() {
        List<Usuario> lista = new ArrayList<>();
        usuarioRepository.findAll().forEach(lista::add);
        return lista;
    }

    // Devuelve un usuario por su id
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Devuelve un usuario por su username
    public Usuario findByUsername(String username) {
        if (username == null || username.isBlank()) return null;
        return usuarioRepository.findByUsername(username).orElse(null);
    }

    // Registra un nuevo usuario con rol USER y contraseña encriptada
    public String registrar(Usuario usuario) {
        if (usuario == null) return "El usuario es nulo";
        if (usuario.getUsername() == null || usuario.getUsername().isBlank()) return "El username es requerido";
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) return "La contraseña es requerida";
        if (usuarioRepository.existsByUsername(usuario.getUsername())) return "El username ya existe";

        usuario.setRol(Rol.USER);
        usuario.setPassword(passwordHash.hash(usuario.getPassword()));
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
        return null;
    }

    // Guarda un usuario encriptando su contraseña
    public void guardar(Usuario usuario) {
        usuario.setPassword(passwordHash.hash(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }
}
