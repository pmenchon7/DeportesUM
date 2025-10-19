package com.universidadmurcia.deportes.infrastructure.config;

import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.model.Team;
import com.universidadmurcia.deportes.domain.repository.CompetitionRepository;
import com.universidadmurcia.deportes.domain.repository.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;

    public DatabaseSeeder(CompetitionRepository competitionRepository, TeamRepository teamRepository) {
        this.competitionRepository = competitionRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (competitionRepository.count() == 0) {
            Competition c = Competition.builder()
                    .nombre("Liga Universitaria 2025")
                    .deporte("Fútbol Sala")
                    .fechaInicio(LocalDate.of(2025, 11, 10))
                    .fechaFin(LocalDate.of(2025, 12, 20))
                    .numPistas(3)
                    .diasReservados(Arrays.asList(LocalDate.of(2025, 11, 10)))
                    .build();
            competitionRepository.save(c);

            teamRepository.save(Team.builder().nombre("Ingeniería FC").competition(c).build());
            teamRepository.save(Team.builder().nombre("Derecho United").competition(c).build());
            teamRepository.save(Team.builder().nombre("Medicina CF").competition(c).build());
            teamRepository.save(Team.builder().nombre("Informática SC").competition(c).build());
            teamRepository.save(Team.builder().nombre("Economía Rovers").competition(c).build());
        }
    }
}
