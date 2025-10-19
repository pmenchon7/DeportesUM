package com.universidadmurcia.deportes.api.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CompetitionDTO {
    private Long id;
    private String nombre;
    private String deporte;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numPistas;
    private List<LocalDate> diasReservados;
}
