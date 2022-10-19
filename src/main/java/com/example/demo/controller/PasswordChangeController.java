package com.example.demo.controller;

import com.example.demo.model.dto.request.UpdatePasswordDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordChangeController {
    @Autowired
    public JoinService joinService;
    @PatchMapping("/api/members/password")
    public ResponseEntity<ResponseDto<?>> changePassword(@RequestBody UpdatePasswordDto dto){
        joinService.updateTeacherPassword(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("비밀번호 변경에 성공하였습니다.").build());
    }
}
