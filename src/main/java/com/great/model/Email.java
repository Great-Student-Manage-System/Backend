package com.great.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 이메일주소를 표현하는 클래스 입니다.
 */
public class Email {
    private final String str;

    /**
     *
     * @param email 이메일 주소 형태의 문자열 입니다
     * @throws IllegalArgumentException email 이 이메일 주소 형식을 만족하지 못할 때
     */
    public Email(String email){
        if (validate(email)){
            this.str = email;
        }else{
            throw new IllegalArgumentException("올바른 이메일이 아닙니다.");
        }
    }
    private boolean validate(String email){
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     *
     * @return 이메일주소를 문자열로 반환합니다.
     */
    @Override
    public String toString(){
        return str;
    }

    /**
     *
     * @param obj 비교하고자 하는 Email 객체입니다.
     * @return Email 객체가 표현하는 이메일 주소값이 같으면 true 다르면 false 를 반환합니다.
     */
    @Override
    public boolean equals(Object obj) {
        Email email = (Email) obj;
        return email.str.contentEquals(this.str);
    }
}
