package com.example.demo.model.dto.request;


import lombok.Getter;
/**
 * 시험성적 수정 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class UpdateRecordDto {
    private int recordId;
    private int examId;
    private int examResult;
}
