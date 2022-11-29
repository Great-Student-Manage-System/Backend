/*
package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
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
public class SelectExamListController {
    @Autowired
    private ExamService examService;
    @Autowired
    private TokenManager tokenManager;

    @GetMapping("/api/exams/{year}")
    public ResponseEntity<ResponseDto<?>> getExams(@RequestHeader("Authorization") String accessTokenString, @PathVariable int year){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectExamsResponseDto examList = examService.getExams(teacherId,LocalDate.ofYearDay(year, 1));
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("시험 목록 조회를 성공했습니다.").data(examList).build());
    }
}
*/
