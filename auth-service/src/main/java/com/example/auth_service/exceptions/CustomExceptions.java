package com.example.auth_service.exceptions;

public class CustomExceptions {
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    
    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
    
    public static class TokenInvalidException extends RuntimeException {
        public TokenInvalidException(String message) {
            super(message);
        }
    }
    
    public static class TokenExpiredException extends RuntimeException {
        public TokenExpiredException(String message) {
            super(message);
        }
    }
    
    public static class EmailSendException extends RuntimeException {
        public EmailSendException(String message) {
            super(message);
        }
    }
    
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
}
