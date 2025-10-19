package com.universidadmurcia.deportes.api.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MatchDTO {
    private Long id;
    private Long competitionId;
    private Long equipoLocalId;
    private Long equipoVisitanteId;
    private LocalDate fecha;
    private int pista;
}
