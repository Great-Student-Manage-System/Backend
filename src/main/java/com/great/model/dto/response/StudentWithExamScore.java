package com.great.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StudentWithExamScore {
    int studentId;
    String name;
    String school;
    int schoolYear;
    int examScore;
    String subjects;
}
