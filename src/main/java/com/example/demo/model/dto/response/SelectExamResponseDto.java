package com.example.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 시험 정보 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
@AllArgsConstructor
@Getter
public class SelectExamResponseDto {
    private int[] gradeCut;
}
