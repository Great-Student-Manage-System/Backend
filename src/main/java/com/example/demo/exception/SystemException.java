package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import org.springframework.lang.NonNull;

public class SystemException extends RuntimeException{
    @NonNull
    private ErrorMessage errorMessage;

    public SystemException(ErrorMessage errorMessage){
        this.errorMessage = errorMessage;
    }

    public ErrorMessage toError(){
        return errorMessage;
    }
}
