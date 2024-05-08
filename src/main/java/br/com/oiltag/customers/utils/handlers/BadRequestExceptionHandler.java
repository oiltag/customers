package br.com.oiltag.customers.utils.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity handleMissingPathVariableException(MissingPathVariableException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
