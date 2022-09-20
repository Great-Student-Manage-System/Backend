package com.example.demo.repository;

import com.example.demo.model.Student;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);
    Optional<Student> findById(int id);
    List<Student> findByName(String name);
    List<Student> findByGrade(int grade);
    List<Student> findBySubject(String subject); //세부 과목 리스트 기준으로 학생들 불러오기
    List<Student> findBySchool(String school);
    List<Student> findByYear(int year);
    List<Student> findByTeacher(int teacherId, int page);
}
