package com.universidadmurcia.deportes.domain.repository;

import com.universidadmurcia.deportes.domain.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
