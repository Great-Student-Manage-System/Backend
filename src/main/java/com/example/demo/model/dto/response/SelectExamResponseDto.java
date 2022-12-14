package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

/**
 * 시험 정보 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
@Builder
@Getter
public class SelectExamResponseDto {
    int examId;
    String examName;
    Date examDate;
    int schoolYear;
    String subject;
    private int[] gradeCut;
}
