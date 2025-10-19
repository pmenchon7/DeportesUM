package com.universidadmurcia.deportes.api.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TeamDTO {
    private Long id;
    private String nombre;
    private Long competitionId;
}
