package com.sistema.dinosaurportalbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContribucionRequest {
    private String titulo;
    private String tipo;
    private String epoca;
    private String categoria;
    private String habitat;
    private String alimentacion;
    private String tamanio;
    private String curiosidades;
    private String imagen;
    private String contenidoHtml;
}
