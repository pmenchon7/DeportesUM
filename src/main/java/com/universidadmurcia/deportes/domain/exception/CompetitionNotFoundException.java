package com.universidadmurcia.deportes.domain.exception;

public class CompetitionNotFoundException extends RuntimeException {
    public CompetitionNotFoundException(Long id) {
        super("Competition not found: " + id);
    }
}
