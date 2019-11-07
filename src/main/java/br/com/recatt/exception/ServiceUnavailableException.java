package br.com.recatt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException {

    private static final long serialVersionUID = -2156065425583182296L;

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
