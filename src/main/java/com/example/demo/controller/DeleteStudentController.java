/*
package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteStudentController {
    @Autowired
    TokenManager tokenManager;
    @Autowired
    StudentService studentService;
    @DeleteMapping("/api/students/{studentId}")
    public ResponseEntity<ResponseDto<?>> delete(@RequestHeader("Authorization") String accessTokenString,@PathVariable int studentId){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.deleteStudent(teacherId,studentId);
        return ResponseEntity.ok(ResponseDto.builder().response("학생을 삭제했습니다.").code(200).build());
    }
}
*/
