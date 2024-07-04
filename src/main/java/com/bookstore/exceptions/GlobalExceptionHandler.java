package com.bookstore.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorDetails> handleAPIException(APIException ex, HttpServletRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .path(request.getRequestURI())
                .message(ex.getMessage())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now().toString())
                .errors(ex.getCause().toString())
                .build();
        return ResponseEntity.status(ex.getStatusCode()).body(errorDetails);
    }
}
