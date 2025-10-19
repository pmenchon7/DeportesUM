package com.universidadmurcia.deportes.application;

import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.model.Match;
import com.universidadmurcia.deportes.domain.model.Team;
import com.universidadmurcia.deportes.domain.repository.CompetitionRepository;
import com.universidadmurcia.deportes.domain.repository.MatchRepository;
import com.universidadmurcia.deportes.domain.repository.TeamRepository;
import com.universidadmurcia.deportes.domain.exception.CompetitionNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class MatchService {

    private final MatchRepository matchRepo;
    private final TeamRepository teamRepo;
    private final CompetitionRepository competitionRepo;

    public MatchService(MatchRepository matchRepo, TeamRepository teamRepo, CompetitionRepository competitionRepo) {
        this.matchRepo = matchRepo;
        this.teamRepo = teamRepo;
        this.competitionRepo = competitionRepo;
    }

    @Transactional
    public Map<String, Object> generateFirstRound(Long competitionId) {
        Competition comp = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));

        if (matchRepo.existsByCompetition(comp)) {
            List<Match> already = matchRepo.findByCompetitionOrderByPistaAscFechaAsc(comp);
            Map<String,Object> res = new HashMap<>();
            res.put("matches", already);
            res.put("unassigned", Collections.emptyList());
            return res;
        }

        List<Team> teams = teamRepo.findByCompetitionOrderByIdAsc(comp);
        List<Match> assigned = new ArrayList<>();
        List<Team> unassigned = new ArrayList<>();

        int numPistas = Math.max(1, comp.getNumPistas());
        int capacity = numPistas * 2;
        LocalDate jornadaDate = comp.getDiasReservados() != null && !comp.getDiasReservados().isEmpty()
                ? comp.getDiasReservados().get(0)
                : comp.getFechaInicio() != null ? comp.getFechaInicio() : LocalDate.now();

        if (teams.size() < 2) {
            unassigned.addAll(teams);
            Map<String,Object> res = new HashMap<>();
            res.put("matches", assigned);
            res.put("unassigned", unassigned);
            return res;
        }

        int maxMatches = capacity;
        int maxTeamsAssignable = maxMatches * 2;

        List<Team> queue = new ArrayList<>(teams);

        int teamsToAssign = Math.min(queue.size(), maxTeamsAssignable);
        List<Team> assignList = new ArrayList<>(queue.subList(0, teamsToAssign));
        if (queue.size() > teamsToAssign) {
            unassigned.addAll(queue.subList(teamsToAssign, queue.size()));
        }

        int matchIndex = 0;
        for (int i = 0; i + 1 < assignList.size(); i += 2) {
            Team home = assignList.get(i);
            Team away = assignList.get(i + 1);

            int pista = (matchIndex % numPistas) + 1;
            Match m = Match.builder()
                    .competition(comp)
                    .equipoLocal(home)
                    .equipoVisitante(away)
                    .fecha(jornadaDate)
                    .pista(pista)
                    .build();

            assigned.add(matchRepo.save(m));
            matchIndex++;
        }

        Map<String,Object> res = new HashMap<>();
        res.put("matches", assigned);
        res.put("unassigned", unassigned);
        return res;
    }

    public List<Match> getMatches(Long competitionId) {
        Competition comp = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));
        return matchRepo.findByCompetitionOrderByPistaAscFechaAsc(comp);
    }

    public List<?> getUnassigned(Long competitionId) {
        Competition comp = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new CompetitionNotFoundException(competitionId));
        List<Team> teams = teamRepo.findByCompetitionOrderByIdAsc(comp);
        List<Match> matches = matchRepo.findByCompetitionOrderByPistaAscFechaAsc(comp);

        Set<Long> assignedTeamIds = new HashSet<>();
        for (Match m : matches) {
            assignedTeamIds.add(m.getEquipoLocal().getId());
            assignedTeamIds.add(m.getEquipoVisitante().getId());
        }

        List<Team> unassigned = new ArrayList<>();
        for (Team t : teams) {
            if (!assignedTeamIds.contains(t.getId())) unassigned.add(t);
        }
        return unassigned;
    }
}
