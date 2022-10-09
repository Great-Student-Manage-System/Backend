package com.example.demo.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 엑세스 토큰과 리프레쉬 토큰을 한번에 다루는 클래스 입니다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tokens {
    private AccessToken accessToken;
    private RefreshToken refreshToken;
}
