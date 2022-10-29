package com.example.demo.controller;

import com.example.demo.model.dto.request.UpdateStudentDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateStudentController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private StudentService studentService;
    @PatchMapping("/api/students")
    public ResponseEntity<ResponseDto<?>> update(@RequestHeader("Authorization") String accessTokenString, @RequestBody UpdateStudentDto dto){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.updateStudent(teacherId,dto);
        return ResponseEntity.ok(ResponseDto.builder().response("학생을 수정했습니다.").code(200).build());
    }
}
