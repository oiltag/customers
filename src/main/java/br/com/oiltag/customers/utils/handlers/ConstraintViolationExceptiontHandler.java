package br.com.oiltag.customers.utils.handlers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ConstraintViolationExceptiontHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> handleConstraintViolationException(ConstraintViolationException e) {
        ArrayList<Object> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

}
