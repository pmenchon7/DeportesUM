package com.universidadmurcia.deportes.domain.repository;

import com.universidadmurcia.deportes.domain.model.Competition;
import com.universidadmurcia.deportes.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByNombreAndCompetition(String nombre, Competition competition);
    List<Team> findByCompetitionOrderByIdAsc(Competition competition);
}
