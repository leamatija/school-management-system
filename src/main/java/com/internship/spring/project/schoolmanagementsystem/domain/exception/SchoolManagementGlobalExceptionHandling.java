package com.internship.spring.project.schoolmanagementsystem.domain.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SchoolManagementGlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Date.from(Instant.now()));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST,getRequiredFields(ex),Date.from(Instant.now()));
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Date.from(Instant.now()));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
    private Map<String,String> getRequiredFields(MethodArgumentNotValidException ex){
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->{errors.put(e.getField(),e.getDefaultMessage());});
        return errors;
    }


    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintException(ConstraintException ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), Date.from(Instant.now()));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        ExceptionResponse resp = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), Date.from(Instant.now()));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}
