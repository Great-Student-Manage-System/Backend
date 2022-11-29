package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.model.dto.request.ChangePasswordDto;
import com.example.demo.model.dto.request.ChangeTeacherDto;
import com.example.demo.model.dto.request.JoinDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;

/**
 * 회원가입과 관련된 로직을 처리하는 Service 계층의 인터페이스 입니다.
 */
public interface JoinService {

    void checkNickName(String nickName);

    /**
     * 이메일 인증을 위한 인증코드를 생성합니다.
     * @param email 인증코드를 생성하고싶은 이메일
     */
    String createEmailCode(Email email);

    /**
     * 인증코드를 인증합니다.
     * @param email 인증받고싶은 이메일
     * @param code 이메일로 전송받은 인증코드
     */
    void certEmail(Email email,String code);

    /**
     * 회원가입을 진행합니다.
     * @param joinDto 회원가입 정보
     */
    void join(JoinDto joinDto);

    /**
     * 선생님의 닉네임을 수정합니다.
     */

}
