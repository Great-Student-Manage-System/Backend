package com.example.demo.model.dto.request;


import lombok.Getter;
/**
 * 선생님의 비밀번호 수정 요청의 파라미터를 담는 클래스입니다.
 */
@Getter
public class ChangePasswordDto {
    int id;
    String password;
    String newPassword;
}
