package com.example.demo.repository;

import com.example.demo.model.Exam;
import com.example.demo.model.ExamStatistics;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExamRepository {
    void save(Exam exam, ExamStatistics examStatistics);
    Optional<ExamStatistics> findById(int examId);
    List<Exam> findByYear(LocalDate year);
    void update(Exam exam, ExamStatistics examStatistics);
}
