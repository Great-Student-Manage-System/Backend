package com.example.demo.service;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.model.dto.response.StudentWithExamScore;

import java.util.List;

/**
 * 학생을 다루는 Service 계층의 인터페이스 입니다.
 */
public interface StudentService {
    /**
     * 특정 선생님이 가르치는 학생을 페이지별로 나누어 조회합니다.
     * @param teacherId 선생님 고유 아이디
     * @param page 페이지 번호
     * @return 학생 정보들
     */
    SelectStudentsResponseDto getStudents(int teacherId, int page);

    List<StudentWithExamScore> getTakingExamStudents(int teacherId, int examId);

    /**
     * 특정 선생님이 가르치는 학생을 추가합니다.
     * @param teacherId 선생님 고유 아이디
     * @param student 학생 정보
     */
    void addStudent(int teacherId, AddStudentDto student);

    void deleteStudent(int teacherId, int studentId);
}
