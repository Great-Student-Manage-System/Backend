package com.example.demo.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
class RefreshToken {
    @Getter(AccessLevel.PACKAGE)
    private String tokenString;
}
