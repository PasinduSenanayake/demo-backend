package com.powerorg.powerplant.util;

import com.powerorg.powerplant.dto.common.ErrorResponse;
import com.powerorg.powerplant.exception.APIException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.powerorg.powerplant.util.Constant.INVALID_REQUEST;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage
        ).collect(Collectors.joining(" and "));
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, INVALID_REQUEST, Collections.singletonList(errorMessage)),
                new HttpHeaders(), HttpStatus.BAD_REQUEST
        );

    }

    @ExceptionHandler({APIException.class})
    public ResponseEntity<Object> handleAPIException(
            APIException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getStatus(), ex.getMessage(), ex.getErrors()), new HttpHeaders(), ex.getStatus());
    }

}
