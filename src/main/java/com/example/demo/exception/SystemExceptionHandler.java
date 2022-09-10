package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorMessage> handle(SystemException e){
        e.printStackTrace();
        return ResponseEntity.status(e.code).body(e.toError());
    }
}
