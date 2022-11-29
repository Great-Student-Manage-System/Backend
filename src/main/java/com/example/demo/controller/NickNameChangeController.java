/*
package com.example.demo.controller;

import com.example.demo.model.dto.request.UpdateTeacherDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NickNameChangeController {
    @Autowired
    public JoinService joinService;
    @PatchMapping("/api/members/myinfo")
    public ResponseEntity<ResponseDto<?>> changeNickName(@RequestBody UpdateTeacherDto dto){
        joinService.updateTeacherNickname(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("닉네임 변경에 성공하였습니다.").build());
    }
}
*/
