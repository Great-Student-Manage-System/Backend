package com.example.demo.controller;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.dto.request.JoinDto;
import com.example.demo.model.dto.request.LoginDto;
import com.example.demo.model.dto.request.RemakeAccessTokenDto;
import com.example.demo.model.dto.response.LoginResponseDto;
import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.EmailService;
import com.example.demo.service.JoinService;
import com.example.demo.token.AccessToken;
import com.example.demo.token.RefreshToken;
import com.example.demo.token.TokenManager;
import com.example.demo.token.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
public class JoinController {
    @Autowired
    private JoinService joinService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private EmailService emailService;

    @GetMapping("/api/members/join/{email}/{code}")
    public ResponseEntity<ResponseDto<?>> checkCertCode(@PathVariable("email") String emailString, @PathVariable String code){
        Email email = new Email(emailString);
        joinService.certEmail(email,code);
        return ResponseEntity.ok(ResponseDto.builder().response("이메일 인증이 완료되었습니다.").code(200).build());
    }

    @PostMapping("/api/members/join")
    public ResponseEntity<ResponseDto<?>> join(@RequestBody JoinDto dto){
        joinService.join(dto);
        return ResponseEntity.ok(ResponseDto.builder()
                .code(200)
                .response("회원가입에 성공했습니다.")
                .build());
    }

    @PostMapping("/api/members/login")
    public ResponseEntity<ResponseDto<?>> login(@RequestBody LoginDto dto, HttpServletResponse response){
        Email email = new Email(dto.getEmail());
        Password password = new Password(dto.getPassword());
        Tokens tokens= tokenManager.makeTokens(email,password);
        String refreshToken = tokens.getRefreshTokenString();
        refreshToken = Base64.getEncoder().encodeToString(refreshToken.getBytes());
        ResponseCookie responseCookie = ResponseCookie.from("refreshToken",refreshToken)
                .httpOnly(true).maxAge(14*24*60*60).sameSite("None").secure(true).path("/").build();
        response.addHeader("Set-Cookie",responseCookie.toString());
        LoginResponseDto responseDto = new LoginResponseDto(tokens.getAccessTokenString());
        ResponseDto<LoginResponseDto> result = ResponseDto.<LoginResponseDto>builder()
                .response("로그인에 성공하였습니다.")
                .data(responseDto)
                .code(200)
                .build();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/members/join/{email}")
    public ResponseEntity<ResponseDto<?>> sendCertCode(@PathVariable("email") String emailString){
        Email email = new Email(emailString);
        String code = joinService.createEmailCode(email);
        emailService.sendEmail(email,code);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("인증번호가 메일로 발송되었습니다.").build());
    }

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
