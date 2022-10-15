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
public class CertCodeCheckController {
    @Autowired
    private JoinService joinService;
    @GetMapping("/api/members/join/{email}/{code}")
    public ResponseEntity<ResponseDto<?>> checkCertCode(@PathVariable("email") String emailString, @PathVariable String code){
        Email email = new Email(emailString);
        joinService.certEmail(email,code);
        return ResponseEntity.ok(ResponseDto.builder().response("이메일 인증이 완료되었습니다.").code(200).build());
    }
}
