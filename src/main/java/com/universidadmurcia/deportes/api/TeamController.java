package com.universidadmurcia.deportes.api;

import com.universidadmurcia.deportes.application.TeamService;
import com.universidadmurcia.deportes.domain.model.Team;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions/{competitionId}/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Team> add(@PathVariable Long competitionId, @RequestBody Team body) {
        Team saved = teamService.register(competitionId, body.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Team>> list(@PathVariable Long competitionId) {
        return ResponseEntity.ok(teamService.listByCompetition(competitionId));
    }
}
