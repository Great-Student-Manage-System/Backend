package com.example.demo.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tokens {
    private AccessToken accessToken;
    private RefreshToken refreshToken;
}
