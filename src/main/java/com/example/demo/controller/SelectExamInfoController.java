package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectExamInfoController {
    @Autowired
    private ExamService examService;

    @GetMapping("/api/exam/{examId}")
    public ResponseEntity<ResponseDto<?>> getExam(@PathVariable int examId){
     SelectExamResponseDto examResult =  examService.getExam(examId);     //ResponseDto가 int[]인 경우 어떻게?
     return ResponseEntity.ok(ResponseDto.builder().data(examResult).response("시험 조회에 성공했습니다.").code(200).build());
    }
}
