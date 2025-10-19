package com.universidadmurcia.deportes.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competition")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String deporte;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int numPistas;

    @ElementCollection
    @CollectionTable(name = "competition_reserved_days", joinColumns = @JoinColumn(name = "competition_id"))
    @Column(name = "reserved_day")
    private List<LocalDate> diasReservados = new ArrayList<>();
}
