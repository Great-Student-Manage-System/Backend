package com.example.demo.service;

import com.example.demo.model.Exam;
import com.example.demo.model.ExamStatistics;

import java.util.Date;
import java.util.List;

public interface ExamService {
    List<Exam> getExams(Date year);
    ExamStatistics getExam(int examId);
}
