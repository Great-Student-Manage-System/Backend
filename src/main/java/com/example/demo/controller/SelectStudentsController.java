package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import com.example.demo.model.dto.response.SelectStudentsResponseDto;
import com.example.demo.service.StudentService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectStudentsController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenManager tokenManager;
    @GetMapping("/api/students/{page}")
    public ResponseEntity<ResponseDto<?>> getStudentList(@RequestHeader("Authorization") String accessTokenString, @PathVariable int page){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectStudentsResponseDto dto = studentService.getStudents(teacherId,page);

        ResponseDto<SelectStudentsResponseDto> responseDto = ResponseDto.<SelectStudentsResponseDto>builder()
                .code(200)
                .data(dto)
                .response("학생 목록 조회에 성공했습니다").build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/api/students/exam/{examId}")
    public ResponseEntity<ResponseDto<?>> getTakingExamStudentList(@RequestHeader("Authorization") String accessTokenString,@PathVariable int examId){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        List<SelectStudentResponseDto> result = studentService.getTakingExamStudents(teacherId,examId);
        ResponseDto<List<SelectStudentResponseDto>> responseDto = ResponseDto.<List<SelectStudentResponseDto>>builder()
                .data(result)
                .response("학생 목록 조회에 성공했습니다")
                .code(200).build();
        return ResponseEntity.ok(responseDto);
    }
}
