package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import lombok.Getter;
import org.springframework.lang.NonNull;

public class SystemException extends RuntimeException{
    @NonNull
    @Getter
    private final ErrorMessage errorMessage;

    public SystemException(ErrorMessage errorMessage){
        this.errorMessage = errorMessage;
    }
}
