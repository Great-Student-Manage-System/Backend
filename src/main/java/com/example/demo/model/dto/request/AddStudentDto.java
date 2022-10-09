package com.example.demo.model.dto.request;


import lombok.Getter;
/**
 * 학생 추가 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class AddStudentDto {
    private String name;
    private int schoolYear;
    private String subjects;
    private String school;
}
