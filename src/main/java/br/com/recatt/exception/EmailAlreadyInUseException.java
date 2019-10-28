package br.com.recatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = 2239637828961536126L;

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
