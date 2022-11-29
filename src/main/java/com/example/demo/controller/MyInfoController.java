/*
package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;
import com.example.demo.service.JoinService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyInfoController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private JoinService joinService;
    @GetMapping("/api/members/myInfo")
    public ResponseEntity<ResponseDto<?>> getTeacher(@RequestHeader("Authorization") String accessTokenString){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectTeacherResponseDto dto = joinService.getTeacher(teacherId);
        return ResponseEntity.ok(ResponseDto.builder().code(200).data(dto).response("자기 정보 조회에 성공하였습니다.").build());
    }
}
*/
