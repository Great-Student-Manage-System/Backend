package com.example.demo.service;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;

public interface StudentService {
    SelectStudentsResponseDto getStudents(int teacherId, int page);
    void addStudent(AddStudentDto student);
}
