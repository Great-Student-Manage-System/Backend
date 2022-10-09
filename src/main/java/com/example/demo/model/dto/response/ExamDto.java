package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExamDto {
    int examId;
    String examName;
    int schoolYear;
    String subject;
}
