package com.universidadmurcia.deportes.api;

import com.universidadmurcia.deportes.application.CompetitionService;
import com.universidadmurcia.deportes.domain.model.Competition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService service;

    public CompetitionController(CompetitionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Competition> create(@RequestBody Competition comp) {
        Competition saved = service.create(comp);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
