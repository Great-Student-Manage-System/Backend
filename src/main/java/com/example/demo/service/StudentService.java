package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents(int page);
    void addStudent(Student student);
}
