package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.model.dto.request.UpdatePasswordDto;
import com.example.demo.model.dto.request.UpdateTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;

/**
 * 회원가입과 관련된 로직을 처리하는 Service 계층의 인터페이스 입니다.
 */
public interface JoinService {
    /**
     * 현재 가입되어있는 이메일인지 확인합니다.
     * @param email 가입여부를 확인하고싶은 이메일
     * @throws com.example.demo.exception.SystemException 이미 가입된 이메일인 경우
     */
    void checkEmail(Email email);

    /**
     * 이메일 인증을 위한 인증코드를 생성합니다.
     * @param email 인증코드를 생성하고싶은 이메일
     */
    void createEmailCode(Email email);

    /**
     *
     * @param email 인증받고싶은 이메일
     * @param code 이메일로 전송받은 인증코드
     */
    void certEmail(Email email,String code);

    /**
     * 선생님의 닉네임을 수정합니다.
     */
    void updateTeacherNickname(UpdateTeacherDto dto);
    void updateTeacherPassword(UpdatePasswordDto dto);
    SelectTeacherResponseDto getTeacher(int teacherId);
}
