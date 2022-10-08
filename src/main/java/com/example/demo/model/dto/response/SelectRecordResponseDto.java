package com.example.demo.model.dto.response;

import lombok.AllArgsConstructor;

/**
 * 성적 조회 요청의 응답으로 사용되는 클래스 입니다.
 */
@AllArgsConstructor
public class SelectRecordResponseDto {
    private int recordId;
    private int examId;
    private int score;
}

