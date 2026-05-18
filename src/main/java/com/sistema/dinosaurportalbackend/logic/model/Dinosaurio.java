package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Representa un dinosaurio en el sistema
@Entity
@Table(name = "dinosaurio")
@Getter
@Setter
public class Dinosaurio {
    // Identificador único del dinosaurio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del dinosaurio
    @Column(nullable = false, length = 100)
    private String nombre;

    // Tipo: carnivoro, herbivoro u omnivoro
    @Column(nullable = false, length = 20)
    private String tipo;

    // Época en que vivió: jurasico, cretacico o triasico
    @Column(nullable = false, length = 20)
    private String epoca;

    // Ruta de la imagen representativa
    @Column(length = 255)
    private String imagen;

    // Lugar donde vivía el dinosaurio
    @Column(length = 255)
    private String habitat;

    // Descripción de qué comía
    @Column(length = 255)
    private String alimentacion;

    // Tamaño aproximado
    @Column(length = 100)
    private String tamanio;

    // Datos curiosos sobre el dinosaurio
    @Lob
    @Column(columnDefinition = "TEXT")
    private String curiosidades;

    // Descripción general del dinosaurio
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Indica si el dinosaurio es visible para los usuarios
    @Column(nullable = false)
    private Boolean publicado;

    // Usuario que propuso este dinosaurio mediante una contribución
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
