package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(int teacherId, int page);
    void addStudent(Student student);
}
