package com.example.demo.model;

import org.springframework.lang.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 비밀번호를 표현하는 클래스 입니다.
 */
public class Password {
    @NonNull private final String pw;

    /**
     *
     * @param password 비밀번호 문자열
     * @throws IllegalArgumentException 비밀번호 생성규칙을 만족하지 못할 때
     */
    public Password(String password){
        if(validate(password)){
            this.pw = password;
        }else {
            throw new IllegalArgumentException("비밀번호 생성규칙에 알맞지 않습니다.");
        }
    }

    private boolean validate(String email){
        //'숫자', '문자' 무조건 1개 이상, '최소 8자에서 최대 20자' 허용(특수문자는 정의된 특수문자만 사용 가능) 하는 정규식
        String regx = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @return 비밀번호를 문자열 형태로 반환합니다.
     */
    @Override
    public String toString() {
        return pw;
    }
}
