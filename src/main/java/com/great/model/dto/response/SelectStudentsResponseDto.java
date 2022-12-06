package com.great.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 학생 목록 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
@AllArgsConstructor
@Getter
public class SelectStudentsResponseDto {
    private int maxPage;
    private List<SelectStudentResponseDto> data;
}
