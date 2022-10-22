package com.example.demo.controller;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddStudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/api/students")
    public ResponseEntity<ResponseDto<?>> addStudent(@RequestHeader("Authorization") String accessTokenString, @RequestBody AddStudentDto dto){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.addStudent(teacherId,dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("학생 추가에 성공했습니다").build());
    }
}
