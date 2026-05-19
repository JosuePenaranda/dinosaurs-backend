package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

// Representa una propuesta de dinosaurio enviada por un usuario para revisión del admin
@Entity
@Table(name = "contribucion")
@Getter
@Setter
public class Contribucion {
    // Identificador único de la contribución
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Nombre del dinosaurio propuesto
    @Column(nullable = false, length = 255)
    private String titulo;

    // Tipo del dinosaurio: carnivoro, herbivoro u omnivoro
    @Column(nullable = false, length = 20)
    private String tipo;

    // Época del dinosaurio: jurasico, cretacico o triasico
    @Column(nullable = false, length = 20)
    private String epoca;

    // Categoría del dinosaurio: terrestre, acuatico o aereo
    @Column(length = 50)
    private String categoria;

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

    // Ruta de la imagen
    @Column(length = 255)
    private String imagen;

    // Descripción detallada del dinosaurio
    @Lob
    @Column(name = "contenido", nullable = false, columnDefinition = "TEXT")
    private String contenido;

    // Estado actual: PENDIENTE, APROBADA o RECHAZADA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoContribucion estado;

    // Fecha en que se envió la contribución
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    // Comentario del admin al aprobar o rechazar
    @Lob
    @Column(name = "observacion_admin", columnDefinition = "TEXT")
    private String observacionAdmin;

    // Usuario que envió la contribución
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
