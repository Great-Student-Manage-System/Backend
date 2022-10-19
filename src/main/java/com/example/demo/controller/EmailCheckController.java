package com.example.demo.controller;

import com.example.demo.model.Email;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailCheckController {
    @Autowired
    private JoinService joinService;
    @GetMapping("/api/members/join/emailCheck/{email}")
    public ResponseEntity<ResponseDto<?>> emailCheck(@PathVariable("email") String emailString){
        Email email = new Email(emailString);
        joinService.checkEmail(email);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("가입 가능한 이메일 입니다.").build());
    }
}
