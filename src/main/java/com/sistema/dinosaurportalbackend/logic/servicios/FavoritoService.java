package com.sistema.dinosaurportalbackend.logic.servicios;

import com.sistema.dinosaurportalbackend.data.FavoritoRepository;
import com.sistema.dinosaurportalbackend.logic.model.Dinosaurio;
import com.sistema.dinosaurportalbackend.logic.model.Favorito;
import com.sistema.dinosaurportalbackend.logic.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Maneja la lógica de los favoritos del usuario
@Service
public class FavoritoService {
    @Autowired private FavoritoRepository favoritoRepository;
    @Autowired private DinosaurioService dinosaurioService;

    // Devuelve todos los favoritos de un usuario
    public List<Favorito> findByUsuarioId(Integer usuarioId) {
        return favoritoRepository.findByUsuario_Id(usuarioId);
    }

    // Agrega un dinosaurio a los favoritos del usuario
    public String agregar(Integer usuarioId, Integer dinosaurioId) {
        if (favoritoRepository.existsByUsuario_IdAndDinosaurio_Id(usuarioId, dinosaurioId))
            return "Ya está en favoritos";

        Dinosaurio dinosaurio = dinosaurioService.findById(dinosaurioId);
        if (dinosaurio == null) return "El dinosaurio no existe";

        Favorito favorito = new Favorito();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        favorito.setUsuario(usuario);
        favorito.setDinosaurio(dinosaurio);
        favoritoRepository.save(favorito);
        return null;
    }

    // Elimina un dinosaurio de los favoritos del usuario
    public String eliminar(Integer usuarioId, Integer dinosaurioId) {
        favoritoRepository.findByUsuario_IdAndDinosaurio_Id(usuarioId, dinosaurioId)
            .ifPresent(favoritoRepository::delete);
        return null;
    }
}
