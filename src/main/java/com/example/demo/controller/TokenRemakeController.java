package com.example.demo.controller;

import com.example.demo.model.dto.request.RemakeAccessTokenDto;
import com.example.demo.model.dto.response.LoginResponseDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.token.AccessToken;
import com.example.demo.token.RefreshToken;
import com.example.demo.token.TokenManager;
import com.example.demo.token.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
public class TokenRemakeController {
    @Autowired
    private TokenManager tokenManager;
    @PutMapping("/api/members/login")
    public ResponseEntity<ResponseDto<?>> remakeToken(@CookieValue(name = "refreshToken") String refreshTokenString, @RequestBody RemakeAccessTokenDto dto, HttpServletResponse response){
        refreshTokenString = new String(Base64.getDecoder().decode(refreshTokenString.getBytes()));
        RefreshToken refreshToken = new RefreshToken(refreshTokenString);
        AccessToken accessToken = new AccessToken(dto.getAccessToken());
        Tokens tokens = new Tokens(accessToken,refreshToken);
        tokens = tokenManager.reMakeTokens(tokens);
        refreshTokenString = tokens.getRefreshTokenString();
        refreshTokenString = Base64.getEncoder().encodeToString(refreshTokenString.getBytes());
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken",refreshTokenString)
                        .httpOnly(true).maxAge(14*24*60*60).sameSite("None").secure(true).path("/").build();
        response.addHeader("Set-Cookie",responseCookie.toString());
        LoginResponseDto responseDto = new LoginResponseDto(tokens.getAccessTokenString());
        ResponseDto<LoginResponseDto> result = ResponseDto.<LoginResponseDto>builder()
                .response("토큰 갱신에 성공하였습니다.")
                .data(responseDto)
                .code(200)
                .build();
        return ResponseEntity.ok(result);
    }
}
