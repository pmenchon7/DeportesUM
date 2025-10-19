package com.universidadmurcia.deportes.application;

import com.universidadmurcia.deportes.domain.exception.CompetitionNotFoundException;
import com.universidadmurcia.deportes.domain.exception.DuplicateRegistrationException;
import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.model.Team;
import com.universidadmurcia.deportes.domain.repository.CompetitionRepository;
import com.universidadmurcia.deportes.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepo;
    private final CompetitionRepository competitionRepo;

    public TeamService(TeamRepository teamRepo, CompetitionRepository competitionRepo) {
        this.teamRepo = teamRepo;
        this.competitionRepo = competitionRepo;
    }

    @Transactional
    public Team register(Long competitionId, String nombre) {
        Competition comp = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));

        if (teamRepo.existsByNombreAndCompetition(nombre, comp)) {
            throw new DuplicateRegistrationException("Team already registered in competition: " + nombre);
        }

        Team t = Team.builder()
                .nombre(nombre)
                .competition(comp)
                .build();

        return teamRepo.save(t);
    }

    public List<Team> listByCompetition(Long competitionId) {
        Competition comp = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));
        return teamRepo.findByCompetitionOrderByIdAsc(comp);
    }
}
