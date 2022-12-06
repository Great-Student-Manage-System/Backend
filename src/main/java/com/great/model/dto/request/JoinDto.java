package com.great.model.dto.request;


import lombok.Getter;
/**
 * 선생님의 회원가입 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class JoinDto {
    private String email;
    private String nickName;
    private String password;
    private String subject;
}
