package com.example.demo.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 엑세스토큰을 표현하는 클래스 입니다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PACKAGE)
class AccessToken {
    private String tokenString;
}
