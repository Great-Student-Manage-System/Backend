package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpMethod;

/**
 * 각종 요청을 정상적으로 처리하지 못했을 때 응답으로 사용하는 클래스 입니다.
 */
@Builder
@Data
public class ErrorMessage {
    private int code;
    private String message;
    @Setter
    private String path;
    @Setter
    private HttpMethod method;
}
