package com.great.service;

import com.great.model.dto.request.ChangePasswordDto;
import com.great.model.dto.request.ChangeTeacherDto;
import com.great.model.dto.response.SelectTeacherResponseDto;

public interface TeacherService {
    void changeTeacherNickname(ChangeTeacherDto dto);   //update -> change
    void changeTeacherPassword(ChangePasswordDto dto);  //update -> change
    SelectTeacherResponseDto getTeacher(int teacherId);
}
