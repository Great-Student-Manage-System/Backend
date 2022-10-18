package com.example.demo.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 리프레쉬 토큰을 표현하는 클래스입니다.
 */
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    @Getter(AccessLevel.PACKAGE)
    private String tokenString;
}
