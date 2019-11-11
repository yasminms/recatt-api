package br.com.recatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4609240367972910179L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}
