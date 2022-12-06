package com.great.application.port.out;

import com.great.model.Email;
import com.great.model.Password;
import com.great.model.dto.request.JoinDto;
import com.great.model.dto.request.ChangePasswordDto;
import com.great.model.dto.request.ChangeTeacherDto;
import com.great.model.dto.response.SelectTeacherResponseDto;

import java.util.Optional;

public interface TeacherRepository {
    void save(JoinDto teacher);
    Optional<SelectTeacherResponseDto> findById(int id);
    Optional<SelectTeacherResponseDto> findByNickName(String nickname);
    void updateNickname(ChangeTeacherDto dto);
    void updatePassword(ChangePasswordDto dto);
    Optional<SelectTeacherResponseDto> findByEmailPassword(Email email, Password password);
}
