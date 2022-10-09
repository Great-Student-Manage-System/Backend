package com.example.demo.repository;

import com.example.demo.model.dto.request.AddExamDto;
import com.example.demo.model.dto.request.UpdateExamDto;
import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;

import java.time.LocalDate;
import java.util.Optional;

public interface ExamRepository {
    void save(AddExamDto dto);
    Optional<SelectExamResponseDto> findById(int examId);
    SelectExamsResponseDto findByYear(LocalDate year);
    void update(UpdateExamDto dto);
}
