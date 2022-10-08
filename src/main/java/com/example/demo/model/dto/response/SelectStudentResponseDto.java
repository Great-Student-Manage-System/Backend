package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 학생 정보 요청의 응답에 사용되는 클래스입니다.
 */
@Builder
@Getter
public class SelectStudentResponseDto {
    int studentId;
    String name;
    String school;
    int grade;
    String subjects;
}
