package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NickNameCheckController {
    @Autowired
    private JoinService joinService;
    @GetMapping("/api/members/join/nickNameCheck/{nickName}")
    public ResponseEntity<ResponseDto<?>> checkNickName(@PathVariable String nickName){
        joinService.checkNickName(nickName);
        return ResponseEntity.ok(ResponseDto.builder().response("사용가능한 닉네입입니다.").code(200).build());
    }
}
