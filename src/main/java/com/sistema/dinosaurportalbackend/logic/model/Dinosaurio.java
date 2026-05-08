package com.sistema.dinosaurportalbackend.logic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dinosaurio")
@Getter
@Setter
public class Dinosaurio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;


    @Column(nullable = false, length = 20)
    private String tipo;


    @Column(nullable = false, length = 20)
    private String epoca;

    @Column(length = 255)
    private String imagen;

    @Column(length = 255)
    private String habitat;

    @Column(length = 255)
    private String alimentacion;

    @Column(length = 100)
    private String tamanio;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String curiosidades;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Boolean publicado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;
}
