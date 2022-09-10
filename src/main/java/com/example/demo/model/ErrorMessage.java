package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorMessage {
    private int code;
    private String message;
    private String path;
    private String method;
}
