package com.sistema.dinosaurportalbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContribucionRequest {
    private String titulo;
    private String tipo;
    private String epoca;
    private String contenidoHtml;
}
