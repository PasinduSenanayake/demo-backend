package com.powerorg.powerplant.exception;

import org.springframework.http.HttpStatus;
import java.util.List;


public class APIException extends RuntimeException {

    private final HttpStatus status;
    private final String message;
    private final List<String> errors;

    public APIException(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public APIException(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.errors = List.of(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
