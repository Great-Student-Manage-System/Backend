package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.Teacher;
import com.example.demo.model.Token;

public interface JoinService {
    void checkEmail(Email email);
    void createEmailCode(Email email);
    Token login(Email email, Password password);
    void join(Teacher teacher);
    Token renewalToken(Token token);
    void updateTeacherNickname(Teacher teacher, String nickname);
    void updateTeacherPassword(Teacher teacher, Password password);
    Teacher getTeacher(int teacherId);
}
