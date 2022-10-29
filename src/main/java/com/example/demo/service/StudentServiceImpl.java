package com.example.demo.service;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public SelectStudentsResponseDto getStudents(int teacherId, int page) {
        int maxPage = 1; //ToDo 최대 페이지 구현 필요
        try{
            maxPage = studentRepository.pageCountByTeacherAndMaxPage(teacherId,10);
        }catch (Exception e){
            log.error(e.toString());
            maxPage = 1;
        }
        List<SelectStudentResponseDto> list = studentRepository.findByTeacher(teacherId, page);
        return new SelectStudentsResponseDto(maxPage,list);
    }

    @Override
    public List<SelectStudentResponseDto> getTakingExamStudents(int teacherId, int examId) {
        return studentRepository.findByTeacherAndExam(teacherId, examId);
    }

    @Override
    public void addStudent(int teacherId,AddStudentDto student) {
        studentRepository.save(teacherId,student);
    }
}