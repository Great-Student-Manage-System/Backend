package com.example.demo.service;

import com.example.demo.model.Email;
import com.example.demo.model.dto.request.UpdatePasswordDto;
import com.example.demo.model.dto.request.UpdateTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;

public interface JoinService {
    void checkEmail(Email email);
    void createEmailCode(Email email);
    void certEmail(Email email,String code);
    void updateTeacherNickname(UpdateTeacherDto dto);
    void updateTeacherPassword(UpdatePasswordDto dto);
    SelectTeacherResponseDto getTeacher(int teacherId);
}
