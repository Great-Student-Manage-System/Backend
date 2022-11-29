package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.Password;
import com.example.demo.model.dto.request.ChangePasswordDto;
import com.example.demo.model.dto.request.ChangeTeacherDto;
import com.example.demo.model.dto.response.ErrorMessage;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TeacherServiceImpl implements TeacherService {
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
