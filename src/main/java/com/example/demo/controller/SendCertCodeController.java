package com.example.demo.controller;

import com.example.demo.model.Email;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.EmailService;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendCertCodeController {
    @Autowired
    private JoinService joinService;
    @Autowired
    private EmailService emailService;
    @PostMapping("/api/members/join/{email}")
    public ResponseEntity<ResponseDto<?>> sendCertCode(@PathVariable("email") String emailString){
        Email email = new Email(emailString);
        String code = joinService.createEmailCode(email);
        emailService.sendEmail(email,code);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("인증번호가 메일로 발송되었습니다.").build());

    }
}
