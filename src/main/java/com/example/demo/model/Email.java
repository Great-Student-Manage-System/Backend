package com.example.demo.model;

import com.example.demo.exception.SystemException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String str;
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

    @Override
    public String toString(){
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        Email email = (Email) obj;
        return email.str.contentEquals(this.str);
    }
}
