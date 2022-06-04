package com.powerorg.powerplant.dto.common;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class ErrorResponse {

    private final Date timestamp;

    private final String status;

    private final String message;

    private final List<String> errors;

    public ErrorResponse( HttpStatus httpStatus, String message, List<String> errors) {

        this.timestamp = new Date();
        this.status = httpStatus.name();
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

}
