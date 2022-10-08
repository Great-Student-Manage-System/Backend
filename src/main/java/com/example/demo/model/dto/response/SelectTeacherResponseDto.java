package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 선생님 개인정보 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
@Getter
@Builder
public class SelectTeacherResponseDto {
    private int id;
    private String email;
    private String nickName;
    private String subject;
}
