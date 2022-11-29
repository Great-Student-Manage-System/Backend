package com.example.demo.controller;

import com.example.demo.model.dto.request.ChangePasswordDto;
import com.example.demo.model.dto.request.ChangeTeacherDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;
import com.example.demo.service.JoinService;
import com.example.demo.service.TeacherService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private JoinService joinService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/api/members/myInfo")
    public ResponseEntity<ResponseDto<?>> getTeacher(@RequestHeader("Authorization") String accessTokenString){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectTeacherResponseDto dto = teacherService.getTeacher(teacherId);
        return ResponseEntity.ok(ResponseDto.builder().code(200).data(dto).response("자기 정보 조회에 성공하였습니다.").build());
    }

    @PatchMapping("/api/members/myinfo")
    public ResponseEntity<ResponseDto<?>> changeNickName(@RequestBody ChangeTeacherDto dto){
        teacherService.changeTeacherNickname(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("닉네임 변경에 성공하였습니다.").build());
    }

    @GetMapping("/api/members/join/nickNameCheck/{nickName}")
    public ResponseEntity<ResponseDto<?>> checkNickName(@PathVariable String nickName){
        joinService.checkNickName(nickName);
        return ResponseEntity.ok(ResponseDto.builder().response("사용가능한 닉네입입니다.").code(200).build());
    }

    @PatchMapping("/api/members/password")
    public ResponseEntity<ResponseDto<?>> changePassword(@RequestBody ChangePasswordDto dto){
        teacherService.changeTeacherPassword(dto);     /** updateTeacherPassword -> changeTeacherPassword **/
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("비밀번호 변경에 성공하였습니다.").build());
    }
    /** updatePassword -> changePassword **/
}
