package com.example.demo.service;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    @Override
    public SelectStudentsResponseDto getStudents(int teacherId, int page) {
        int maxPage = 1; //ToDo 최대 페이지 구현 필요
        List<SelectStudentResponseDto> list = studentRepository.findByTeacher(teacherId, page);
        return new SelectStudentsResponseDto(maxPage,list);
    }

    @Override
    public void addStudent(int teacherId,AddStudentDto student) {
        studentRepository.save(teacherId,student);
    }
}