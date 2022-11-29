/*
package com.example.demo.controller;

import com.example.demo.model.dto.request.AddRecordDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStudentGradeController {
    @Autowired
    private StudentRecordService studentRecordService;

    @PostMapping("/api/students/exam/result")
    public ResponseEntity<ResponseDto<?>> addStudentRecord(@RequestBody AddRecordDto dto){
            studentRecordService.addStudentRecord(dto);
            return ResponseEntity.ok(ResponseDto.builder().code(201).response("성적이 추가되었습니다.").build());
    }
}
*/
