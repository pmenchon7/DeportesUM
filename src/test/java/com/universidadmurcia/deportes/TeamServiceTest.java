package com.universidadmurcia.deportes;

import com.universidadmurcia.deportes.application.TeamService;
import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.repository.CompetitionRepository;
import com.universidadmurcia.deportes.domain.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.universidadmurcia.deportes.domain.model.Team;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TeamServiceTest {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private CompetitionRepository competitionRepo;

    @Test
    void contextLoads() {
        Competition c = Competition.builder()
                .nombre("Test")
                .deporte("TestSport")
                .numPistas(2)
                .build();
        competitionRepo.save(c);

        TeamService service = new TeamService(teamRepo, competitionRepo);
        Team t = service.register(c.getId(), "Equipo A");

        assertThat(t.getId()).isNotNull();
        assertThat(teamRepo.existsByNombreAndCompetition("Equipo A", c)).isTrue();
    }
}
