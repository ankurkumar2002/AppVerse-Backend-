package com.example.auth_service.exceptions;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.auth_service.exceptions.CustomExceptions.EmailSendException;
import com.example.auth_service.exceptions.CustomExceptions.InvalidCredentialsException;
import com.example.auth_service.exceptions.CustomExceptions.TokenExpiredException;
import com.example.auth_service.exceptions.CustomExceptions.TokenInvalidException;
import com.example.auth_service.exceptions.CustomExceptions.UserNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(
            new ErrorResponse("Validation failed"+ errors),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({
        UserNotFoundException.class,
        InvalidCredentialsException.class,
        TokenInvalidException.class,
        TokenExpiredException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomExceptions(RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex instanceof InvalidCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(
            new ErrorResponse(ex.getMessage()),
            status
        );
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<ErrorResponse> handleEmailExceptions(EmailSendException ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Failed to send email: " + ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(
            new ErrorResponse("Internal server error: " + ex.getMessage()),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Error response DTO
    @Getter
    @AllArgsConstructor
    private static class ErrorResponse {
        private String message;
        private List<String> details;
        private Instant timestamp = Instant.now();

        public ErrorResponse(String message) {
            this.message = message;
            this.details = Collections.emptyList();
        }
    }
}