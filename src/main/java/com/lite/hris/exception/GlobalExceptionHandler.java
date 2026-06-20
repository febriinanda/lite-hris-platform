package com.lite.hris.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DocumentCategoryException.class)
    public ResponseEntity<ErrorResponse> handleDocCategory(DocumentCategoryException ex){
        ErrorResponse err = new ErrorResponse();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(ex.getMessage());

        return ResponseEntity.status(err.getStatus())
                .body(err);
    }
}
