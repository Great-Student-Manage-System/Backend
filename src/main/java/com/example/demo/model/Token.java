package com.example.demo.model;

public interface Token {
    public String getAccessToken();
    public String getRefreshToken();
    public Token renewalToken(String refreshToken);
}
