package com.universidadmurcia.deportes.domain.exception;

public class DuplicateRegistrationException extends RuntimeException {
    public DuplicateRegistrationException(String msg) {
        super(msg);
    }
}
