package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;
import com.example.demo.service.ExamService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping("/api/exam/{examId}")
    public ResponseEntity<ResponseDto<?>> getExam(@PathVariable int examId){
        SelectExamResponseDto examResult =  examService.getExam(examId);     //ResponseDto가 int[]인 경우 어떻게?
        return ResponseEntity.ok(ResponseDto.builder().data(examResult).response("시험 조회에 성공했습니다.").code(200).build());
    }

    @Autowired
    private TokenManager tokenManager;

    @GetMapping("/api/exams/{year}")
    public ResponseEntity<ResponseDto<?>> getExamList(@RequestHeader("Authorization") String accessTokenString, @PathVariable int year){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectExamsResponseDto examList = examService.getExams(teacherId, LocalDate.ofYearDay(year, 1));
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("시험 목록 조회를 성공했습니다.").data(examList).build());
    }   /** 메서드명 getExams -> getExamList **/
}
