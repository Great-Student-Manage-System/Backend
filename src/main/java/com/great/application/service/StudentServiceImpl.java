package com.great.application.service;

import com.great.application.port.in.StudentService;
import com.great.model.dto.request.AddStudentDto;
import com.great.model.dto.request.ChangeStudentDto;
import com.great.model.dto.response.SelectStudentResponseDto;
import com.great.model.dto.response.SelectStudentsResponseDto;
import com.great.model.dto.response.StudentWithExamScore;
import com.great.application.port.out.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public SelectStudentsResponseDto getStudents(int teacherId, int page) {
        int maxPage;
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
    public List<StudentWithExamScore> getTakingExamStudents(int teacherId, int examId) {
        return studentRepository.findByTeacherAndExam(teacherId, examId);
    }

    @Override
    public void addStudent(int teacherId, AddStudentDto student) {
        studentRepository.save(teacherId,student);
    }

    @Override
    public void deleteStudent(int teacherId, int studentId) {
        studentRepository.deleteStudent(teacherId,studentId);
    }

    @Override
    public void changeStudent(int teacherId, ChangeStudentDto student) {
        studentRepository.updateStudent(teacherId, student);
    }
}