package com.spring.wmh.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus; 

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
	
	

	    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	    public ResponseEntity<String> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
	        // Log and send a meaningful response
	        return new ResponseEntity<>("Database constraint violated: " + ex.getMessage(), HttpStatus.CONFLICT);
	    }
	    
//	    @ExceptionHandler(CustomAPIException.class)
//	    public ResponseEntity<String> handleCustomAPIException(CustomAPIException ex) {
//	        // Handle your custom exception
//	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//	    }
}
