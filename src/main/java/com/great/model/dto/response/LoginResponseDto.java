package com.great.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 선생님의 로그인 요청의 응답으로 사용되는 클래스 입니다.
 */
@AllArgsConstructor
@Getter
public class LoginResponseDto {
    private String accessToken;
}
