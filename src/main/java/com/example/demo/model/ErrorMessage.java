package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
public class ErrorMessage {
    private int code;
    private String message;
    @Setter
    private String path;
    private String method;
}
