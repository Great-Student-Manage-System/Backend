package com.example.demo.service;

import com.example.demo.model.*;

public interface JoinService {
    void checkEmail(Email email);
    void createEmailCode(Email email);
    void certEmail(Email email,String code);

    Token login(Email email, Password password);
    void join(Teacher teacher);
    Token renewalToken(Token token);
    void updateTeacherNickname(Teacher teacher, String nickname);
    void updateTeacherPassword(UpdtatePasswordDto passwordDto);
    Teacher getTeacher(int teacherId);
}
