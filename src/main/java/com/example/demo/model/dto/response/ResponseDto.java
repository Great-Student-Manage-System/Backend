package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * api 공통 응답 규약을 지키기 위한 Wrapper 클래스
 * @param <T> model.dto.response 패키지에 포함되는 다른 클래스들
 */
@Builder
@Getter
public class ResponseDto<T> {
    private int code;
    private String response;
    private T data;
}
