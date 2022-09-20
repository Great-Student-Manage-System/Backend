package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.ErrorMessage;
import com.example.demo.model.Exam;
import com.example.demo.model.ExamStatistics;
import com.example.demo.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Override
    public List<Exam> getExams(LocalDate year) {
        return examRepository.findByYear(year);
    }

    @Override
    public ExamStatistics getExam(int examId) {
        Optional<ExamStatistics> result = examRepository.findById(examId);
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
