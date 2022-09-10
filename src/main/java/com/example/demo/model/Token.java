package com.example.demo.model;

public interface Token {
    public String getAccessToken();
    public String getRefreshToken();
    public boolean renewalAccessToken(String refreshToken);
}
