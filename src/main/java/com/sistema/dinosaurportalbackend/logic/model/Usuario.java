package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Representa un usuario del sistema
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    // Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre de usuario para iniciar sesión
    @Column(nullable = false, unique = true, length = 100)
    private String username;

    // Contraseña encriptada con BCrypt
    @Column(nullable = false, length = 255)
    private String password;

    // Rol del usuario: ADMIN o USER
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;

    // Indica si el usuario puede iniciar sesión
    @Column(nullable = false)
    private Boolean activo;
}
