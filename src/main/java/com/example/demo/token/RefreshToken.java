package com.example.demo.token;

import lombok.NoArgsConstructor;
@NoArgsConstructor
class RefreshToken {
    private String tokenString;

    RefreshToken(int id){

    }
    boolean isValid(){
        return false;
    }
    int getId(){
        return 1;
    }
}
