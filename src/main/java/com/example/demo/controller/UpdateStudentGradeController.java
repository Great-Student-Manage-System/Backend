package com.example.demo.controller;

import com.example.demo.model.dto.request.AddRecordDto;
import com.example.demo.model.dto.request.UpdateRecordDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStudentGradeController {
    @Autowired
    private StudentRecordService studentRecordService;

    @PatchMapping("/api/students/{studentId}")
    public ResponseEntity<ResponseDto<?>> updateStudentRecord(@RequestBody UpdateRecordDto dto){
        studentRecordService.updateStudentRecord(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("성적을 변경했습니다.").build());
    }

}
