package com.example.demo.model.dto.request;

import lombok.Getter;

import java.time.LocalDate;

/**
 * 시험정보 수정 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class UpdateExamDto {
    private int examId;
    private String name;
    private LocalDate examDate;
    private int[] gradeCut;
    private String subject;
}
