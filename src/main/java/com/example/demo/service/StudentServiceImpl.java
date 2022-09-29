package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;
    @Override
    public List<Student> getStudents(int teacherId, int page) {
        return studentRepository.findByTeacher(teacherId, page);
    }

    @Override
    public void addStudent(Student student) {

        studentRepository.save(student);
        //student 생성자에 빠진 값 있는지 확인
    }
}