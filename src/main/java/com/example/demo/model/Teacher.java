package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Teacher {
    private int id;
    private Email email;
    private Subject subject;
    private String nickName;
    private Password password;
}
