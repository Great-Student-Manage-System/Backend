package com.great.application.port.out;

import com.great.model.dto.request.AddExamDto;
import com.great.model.dto.request.ChangeExamDto;
import com.great.model.dto.response.SelectExamResponseDto;
import com.great.model.dto.response.SelectExamsResponseDto;

import java.time.LocalDate;
import java.util.Optional;

public interface ExamRepository {
    void save(AddExamDto dto);
    Optional<SelectExamResponseDto> findById(int examId);
    SelectExamsResponseDto findByYearAndTeacher(LocalDate year, int teacherId);
    void update(ChangeExamDto dto);
}
