package com.example.demo.repository;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(int TeacherId,AddStudentDto student);
    Optional<SelectStudentResponseDto> findById(int id);
    List<SelectStudentResponseDto> findByName(String name);
    List<SelectStudentResponseDto> findByGrade(int grade);
    List<SelectStudentResponseDto> findBySubject(String subject); //세부 과목 리스트 기준으로 학생들 불러오기
    List<SelectStudentResponseDto> findBySchool(String school);
    List<SelectStudentResponseDto> findByTeacher(int teacherId, int page);
    List<SelectStudentResponseDto> findByTeacherAndExam(int teacherId, int examId);
}
