package com.example.demo.controller;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStudentController {
    @Autowired
    private static StudentService studentService;
    public ResponseEntity<ResponseDto<?>> addStudent(AddStudentDto dto){
        int teacherId = 1;
        studentService.addStudent(teacherId,dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("학생 추가에 성공했습니다").build());
    }
}
