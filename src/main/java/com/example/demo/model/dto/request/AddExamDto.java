package com.example.demo.model.dto.request;

import lombok.Getter;

import java.time.LocalDate;

/**
 * 시험 생성 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class AddExamDto {
    private String name;
    private LocalDate examDate;
    private int[] gradeCut;
    private String subject;
    private int schoolYear;

    public String getGradeCut(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int a : gradeCut) {
            stringBuilder.append(a).append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        return stringBuilder.toString();
    }
}
