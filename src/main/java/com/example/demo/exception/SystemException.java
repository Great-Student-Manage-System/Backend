package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;

public class SystemException extends RuntimeException{
    int code;
    String path;
    String method;
    String msg;
    public SystemException(String msg){
        super(msg);
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
