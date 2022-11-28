package com.example.demo.controller;

import com.example.demo.model.dto.request.UpdateRecordDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectRecordResponseDto;
import com.example.demo.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RecordController {
    @Autowired
    private StudentRecordService recordService;

    @GetMapping("/api/students/{studentId}/{subject}/{year}")
    public ResponseEntity<ResponseDto<?>> getRecords(@PathVariable int studentId, @PathVariable String subject, @PathVariable int year){
        List<SelectRecordResponseDto> result = recordService.getStudentRecords(studentId,subject, LocalDate.ofYearDay(year,1));
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("성적조회에 성공했습니다").data(result).build());
    }

    @Autowired
    private StudentRecordService studentRecordService;

    @PatchMapping("/api/students/{studentId}")
    public ResponseEntity<ResponseDto<?>> updateStudentRecord(@RequestBody UpdateRecordDto dto){
        studentRecordService.updateStudentRecord(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("성적을 변경했습니다.").build());
    }
}
