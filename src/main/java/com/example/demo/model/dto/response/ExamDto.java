package com.example.demo.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class ExamDto {
    int examId;
    String examName;
    Date examDate;
    int schoolYear;
    String subject;
}
