package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto<T> {
    private int code;
    private String response;
    private T data;
}
