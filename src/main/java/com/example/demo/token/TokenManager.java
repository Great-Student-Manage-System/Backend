package com.example.demo.token;

import com.example.demo.model.Email;
import com.example.demo.model.Password;

/**
 * 로그인시 인증 토큰을 생성하는 역할을 하는 인터페이스 입니다.
 */
public interface TokenManager {
    /**
     * 엑세스 토큰으로부터 사용자의 고유 아이디를 추출합니다.
     * @param token 엑세스 토큰입니다.
     * @return 사용자의 고유 아이디
     * @throws com.example.demo.exception.SystemException 엑세스토큰이 만료되었거나 잘못된 경우
     */
    int getIdFromAccessToken(AccessToken token);

    /**
     * 리프레쉬 토큰과 만료된 엑세스토큰을 이용하여 토큰을 재발급합니다.
     * @param tokens 기존의 리프레쉬 토큰과 엑세스토큰
     * @return 새로 생성된 엑세스 토큰과 리프레쉬 토큰
     * @throws com.example.demo.exception.SystemException 리프레쉬 토큰이 만료되었거나 잘못된 경우.
     */
    Tokens reMakeTokens(Tokens tokens);

    /**
     * 이메일과 비밀번호로부터 새로운 인증토큰을 생성합니다.
     * @param email 이메일
     * @param password 비밀번호
     * @return 새로 생성된 엑세스 토큰과 리프레쉬 토큰
     * @throws com.example.demo.exception.SystemException 이메일이나 비밀번호가 잘못된 경우
     */
    Tokens makeTokens(Email email, Password password);
}
