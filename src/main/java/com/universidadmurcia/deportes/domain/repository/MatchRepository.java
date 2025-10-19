package com.universidadmurcia.deportes.domain.repository;

import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByCompetitionOrderByPistaAscFechaAsc(Competition competition);
    boolean existsByCompetition(Competition competition);
}
