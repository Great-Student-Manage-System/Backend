package com.example.demo.model.dto.response;

import java.util.List;

/**
 * 시험 목록 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
public class SelectExamsResponseDto {
    private List<ExamDto> data;
    private static class ExamDto {
        int examId;
        String examName;
        int schoolYear;
        String subject;
    }
}
