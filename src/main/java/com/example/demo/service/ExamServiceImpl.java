package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.dto.response.ErrorMessage;
import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;
import com.example.demo.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Override
    public SelectExamsResponseDto getExams(int teacherId,LocalDate year) {
        return examRepository.findByYearAndTeacher(year,teacherId);
    }

    @Override
    public SelectExamResponseDto getExam(int examId) {
        Optional<SelectExamResponseDto> result = examRepository.findById(examId);
        return result.orElseThrow(() -> {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("examId가 잘못되었습니다.")
                    .code(404)
                    .method(HttpMethod.GET)
                    .build();
            return new SystemException(errorMessage);
        });
    }
}
