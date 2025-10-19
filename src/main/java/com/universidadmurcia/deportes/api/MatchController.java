package com.universidadmurcia.deportes.api;

import com.universidadmurcia.deportes.application.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competitions/{competitionId}/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@PathVariable Long competitionId) {
        return ResponseEntity.ok(matchService.generateFirstRound(competitionId));
    }

    @GetMapping
    public ResponseEntity<?> list(@PathVariable Long competitionId) {
        return ResponseEntity.ok(matchService.getMatches(competitionId));
    }

    @GetMapping("/unassigned")
    public ResponseEntity<?> unassigned(@PathVariable Long competitionId) {
        return ResponseEntity.ok(matchService.getUnassigned(competitionId));
    }
}
