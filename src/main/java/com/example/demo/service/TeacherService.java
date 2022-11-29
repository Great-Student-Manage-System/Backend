package com.example.demo.service;

import com.example.demo.model.dto.request.ChangePasswordDto;
import com.example.demo.model.dto.request.ChangeTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;

public interface TeacherService {
    void changeTeacherNickname(ChangeTeacherDto dto);   //update -> change
    void changeTeacherPassword(ChangePasswordDto dto);  //update -> change
    SelectTeacherResponseDto getTeacher(int teacherId);
}
