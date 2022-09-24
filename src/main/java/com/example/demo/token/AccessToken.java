package com.example.demo.token;

import com.example.demo.exception.SystemException;
import com.example.demo.model.ErrorMessage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class AccessToken {
    private String tokenString;

    AccessToken(int id){

    }
    int getId(){
        if(isValid()){
            return 1; //Todo 구현 필요
        }else {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("엑세스 토큰이 만료되었습니다.")
                    .code(401)
                    .build();
            throw new SystemException(errorMessage);
        }
    }
    boolean isValid(){
        return false;
    }
}
