package com.example.demo.model.dto.response;

import java.util.List;

/**
 * 학생 목록 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
public class SelectStudentsResponseDto {
    private int maxPage;
    private List<StudentDto> data;
    private static class StudentDto{
        int studentId;
        String name;
        String school;
        int grade;
        String subjects;
    }
}
