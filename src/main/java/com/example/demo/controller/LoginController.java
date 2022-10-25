package com.example.demo.controller;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.dto.request.LoginDto;
import com.example.demo.model.dto.response.LoginResponseDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.token.TokenManager;
import com.example.demo.token.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
public class LoginController {
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/api/members/login")
    public ResponseEntity<ResponseDto<?>> login(@RequestBody LoginDto dto, HttpServletResponse response){
        Email email = new Email(dto.getEmail());
        Password password = new Password(dto.getPassword());
        Tokens tokens= tokenManager.makeTokens(email,password);
        String refreshToken = tokens.getRefreshTokenString();
        refreshToken = Base64.getEncoder().encodeToString(refreshToken.getBytes());
        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(14*24*60*60);
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("test","test");
        response.addCookie(cookie1);
        LoginResponseDto responseDto = new LoginResponseDto(tokens.getAccessTokenString());
        ResponseDto<LoginResponseDto> result = ResponseDto.<LoginResponseDto>builder()
                .response("로그인에 성공하였습니다.")
                .data(responseDto)
                .code(200)
                .build();
        return ResponseEntity.ok(result);
    }
}
