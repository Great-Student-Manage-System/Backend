package com.example.demo.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter(AccessLevel.PACKAGE)
class AccessToken {
    private String tokenString;
}
