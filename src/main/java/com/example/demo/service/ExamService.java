package com.example.demo.service;

import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;

import java.time.LocalDate;

public interface ExamService {
    SelectExamsResponseDto getExams(LocalDate year);
    SelectExamResponseDto getExam(int examId);
}
