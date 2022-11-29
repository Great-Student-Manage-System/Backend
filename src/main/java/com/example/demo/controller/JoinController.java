/*
package com.example.demo.controller;

import com.example.demo.model.dto.request.JoinDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    @Autowired
    private JoinService joinService;
    @PostMapping("/api/members/join")
    public ResponseEntity<ResponseDto<?>> join(@RequestBody JoinDto dto){
        joinService.join(dto);
        return ResponseEntity.ok(ResponseDto.builder()
                .code(200)
                .response("회원가입에 성공했습니다.")
                .build());
    }
}
*/
