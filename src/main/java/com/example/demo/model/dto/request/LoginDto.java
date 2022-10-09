package com.example.demo.model.dto.request;


import lombok.Getter;
/**
 * 선생님의 로그인 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class LoginDto {
    String email;
    String password;
}
