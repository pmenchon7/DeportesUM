package com.universidadmurcia.deportes.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = {"nombre", "competition_id"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;
}
