package com.example.demo.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String str;
    public Email(String email){
        if (validate(email)){
            this.str = email;
        }else{
            throw new
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
}
