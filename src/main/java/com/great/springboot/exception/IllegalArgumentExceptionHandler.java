package com.great.springboot.exception;

import com.great.model.dto.response.ErrorMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handle(IllegalArgumentException e, HttpServletRequest request){
        e.printStackTrace();
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(e.getMessage())
                .code(400).build();
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setMethod(HttpMethod.valueOf(request.getMethod()));
        int status = errorMessage.getCode();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
