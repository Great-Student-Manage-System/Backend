package com.great.application.service;

import com.great.application.port.in.TeacherService;
import com.great.springboot.exception.SystemException;
import com.great.model.Password;
import com.great.model.dto.request.ChangePasswordDto;
import com.great.model.dto.request.ChangeTeacherDto;
import com.great.model.dto.response.ErrorMessage;
import com.great.model.dto.response.SelectTeacherResponseDto;
import com.great.application.port.out.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public void changeTeacherNickname(ChangeTeacherDto dto) {
        try {
            teacherRepository.updateNickname(dto);
        }catch (DataAccessException e){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("닉네임이 중복됩니다.")
                    .code(403).build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public void changeTeacherPassword(ChangePasswordDto dto) {
        Password newPassword = new Password(dto.getNewPassword()); // Password 생성자에는 비밀번호 생성규칙 만족여부 체크 로직이 있다.
        teacherRepository.updatePassword(dto);
    }

    @Override
    public SelectTeacherResponseDto getTeacher(int teacherId) {
        Optional<SelectTeacherResponseDto> result = teacherRepository.findById(teacherId);
        return result.orElseThrow(()->{
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("teacherId가 잘못되었습니다.")
                    .code(404)
                    .method(HttpMethod.GET)
                    .build();
            return new SystemException(errorMessage);
        });
    }
}