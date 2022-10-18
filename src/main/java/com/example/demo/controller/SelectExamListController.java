package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;
import com.example.demo.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class SelectExamListController {
    @Autowired
    private ExamService examService;

    @GetMapping("/api/exam/{year}")
    public ResponseEntity<ResponseDto<?>> getExams(@PathVariable int year){
        SelectExamsResponseDto examList = examService.getExams(LocalDate.ofYearDay(year, 1));
        return ResponseEntity.ok(ResponseDto.builder().code(200).data(examList).build());
    }
}
