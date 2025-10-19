package com.universidadmurcia.deportes.application;

import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.repository.CompetitionRepository;
import com.universidadmurcia.deportes.domain.exception.CompetitionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionRepository repo;

    public CompetitionService(CompetitionRepository repo) {
        this.repo = repo;
    }

    public Competition create(Competition competition) {
        return repo.save(competition);
    }

    public Competition findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
    }

    public List<Competition> listAll() {
        return repo.findAll();
    }
}
