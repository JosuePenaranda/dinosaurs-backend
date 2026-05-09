package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "contribucion")
@Getter
@Setter
public class Contribucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, length = 255)
    private String titulo;


    @Column(nullable = false, length = 20)
    private String tipo;


    @Column(nullable = false, length = 20)
    private String epoca;

    @Column(length = 20)
    private String categoria;

    @Column(length = 255)
    private String habitat;

    @Column(length = 255)
    private String alimentacion;

    @Column(length = 100)
    private String tamanio;

    @Column(length = 255)
    private String imagen;

    @Lob
    @Column(name = "curiosidades", columnDefinition = "TEXT")
    private String curiosidades;

    @Lob
    @Column(name = "contenido_html", nullable = false, columnDefinition = "TEXT")
    private String contenidoHtml;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoContribucion estado;

    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Lob
    @Column(name = "observacion_admin", columnDefinition = "TEXT")
    private String observacionAdmin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
