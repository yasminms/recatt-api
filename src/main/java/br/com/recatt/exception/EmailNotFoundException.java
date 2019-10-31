package br.com.recatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5686320512275900289L;

    public EmailNotFoundException(String message) {
        super(message);
    }
}
