package com.great.springboot.exception;

import com.great.model.dto.response.ErrorMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * SystemException 이 발생하였을 때 이를 감지하여 클라이언트에게 에러 메시지를 응답하는 핸들러 입니다.
 */
@RestControllerAdvice
public class SystemExceptionHandler {
    /**
     * SystemException 발생하였을 때 실제로 동작하는 메서드 입니다.
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorMessage> handle(SystemException e, HttpServletRequest request){
        e.printStackTrace();
        ErrorMessage errorMessage = e.getErrorMessage();
        errorMessage.setPath(request.getRequestURI());
        errorMessage.setMethod(HttpMethod.valueOf(request.getMethod()));
        int status = errorMessage.getCode();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
