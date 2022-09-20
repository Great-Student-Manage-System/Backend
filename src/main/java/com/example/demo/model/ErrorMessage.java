package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Builder
@Data
public class ErrorMessage {
    private int code;
    private String message;
    @Setter
    private String path;
    private HttpMethod method;
}
