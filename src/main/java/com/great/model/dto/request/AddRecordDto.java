package com.great.model.dto.request;

import lombok.Getter;
/**
 * 시험성적 추가 요청의 파라미터들을 담는 클래스 입니다.
 */
@Getter
public class AddRecordDto {
    private int studentId;
    private int examId;
    private String subject;
    private int examScore;
}
