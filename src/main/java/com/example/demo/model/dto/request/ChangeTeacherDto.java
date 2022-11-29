package com.example.demo.model.dto.request;


import lombok.Getter;
/**
 * 선생님의 회원정보 변경 요청을 담는 클래스 입니다.
 */
@Getter
public class ChangeTeacherDto {
    int id;
    String nickName;
}
