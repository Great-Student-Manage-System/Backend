package com.example.demo.token;

import com.example.demo.model.Email;
import com.example.demo.model.Password;

public interface TokenManager {
    int getIdFromAccessToken(AccessToken token);
    Tokens reMakeTokens(Tokens tokens);
    Tokens makeTokens(Email email, Password password);
}
