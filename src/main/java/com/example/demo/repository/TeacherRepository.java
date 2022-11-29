package com.example.demo.repository;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.dto.request.JoinDto;
import com.example.demo.model.dto.request.ChangePasswordDto;
import com.example.demo.model.dto.request.ChangeTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;

import java.util.Optional;

public interface TeacherRepository {
    void save(JoinDto teacher);
    Optional<SelectTeacherResponseDto> findById(int id);
    Optional<SelectTeacherResponseDto> findByNickName(String nickname);
    void updateNickname(ChangeTeacherDto dto);
    void updatePassword(ChangePasswordDto dto);
    Optional<SelectTeacherResponseDto> findByEmailPassword(Email email, Password password);
}
