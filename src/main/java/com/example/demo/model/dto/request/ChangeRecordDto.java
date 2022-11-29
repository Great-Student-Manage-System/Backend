package com.example.demo.model.dto.request;


import lombok.Getter;
import lombok.NonNull;

/**
 * 시험성적 수정 요청의 파라미터를 담는 클래스 입니다.
 */
@Getter
public class ChangeRecordDto {
    @NonNull
    private int recordId;
    private Integer examId;
    private Integer examResult;
}
