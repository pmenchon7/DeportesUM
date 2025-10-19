package com.universidadmurcia.deportes.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "match_table")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(optional = false)
    @JoinColumn(name = "home_team_id")
    private Team equipoLocal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "away_team_id")
    private Team equipoVisitante;

    private LocalDate fecha;
    private int pista;
}
