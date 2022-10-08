package com.example.demo.exception;

import com.example.demo.model.dto.response.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class SystemExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorMessage> handle(SystemException e, HttpServletRequest request){
        e.printStackTrace();
        ErrorMessage errorMessage = e.getErrorMessage();
        errorMessage.setPath(request.getRequestURI());
        int status = errorMessage.getCode();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
