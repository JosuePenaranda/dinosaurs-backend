package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Representa un dinosaurio marcado como favorito por un usuario
@Entity
@Table(name = "favorito", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"usuario_id", "dinosaurio_id"})
})
@Getter
@Setter
public class Favorito {
    // Identificador único del favorito
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Usuario que marcó el favorito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Dinosaurio marcado como favorito
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dinosaurio_id", nullable = false)
    private Dinosaurio dinosaurio;
}
