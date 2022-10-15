package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class SelectStudentsController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/api/students/{page}")
    public ResponseEntity<ResponseDto<?>> getStudentList(@PathVariable int page){
        int teacherID = 1;
        SelectStudentsResponseDto dto = studentService.getStudents(teacherID,page);

        ResponseDto<SelectStudentsResponseDto> responseDto = ResponseDto.<SelectStudentsResponseDto>builder()
                .code(200)
                .data(dto)
                .response("학생 목록 조회에 성공했습니다").build();
        return ResponseEntity.ok(responseDto);
    }
}
