package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;

public class SystemException extends RuntimeException{
    @NonNull
    private ErrorMessage errorMessage;

    public SystemException(ErrorMessage errorMessage){
        this.errorMessage = errorMessage;
    }

    public ErrorMessage toError(){
        ErrorMessage.ErrorMessageBuilder builder = ErrorMessage.builder();
        builder.code(code)
                .message(msg)
                .path(path)
                .method(method);
        return builder.build();
    }
}
