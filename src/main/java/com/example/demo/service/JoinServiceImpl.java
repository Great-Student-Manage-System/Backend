package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.Email;
import com.example.demo.model.dto.response.ErrorMessage;
import com.example.demo.model.dto.request.UpdatePasswordDto;
import com.example.demo.model.dto.request.UpdateTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;
import com.example.demo.repository.CertRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class JoinServiceImpl implements JoinService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CertRepository certRepository;
    @Override
    public void checkEmail(Email email) {
        if(!certRepository.canJoin(email)){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("이미 사용중인 이메일입니다.")
                    .code(409)
                    .method(HttpMethod.GET)
                    .build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public String createEmailCode(Email email) {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        certRepository.saveEmailCod(email,code+"");
        return code+"";
    }
    @Override
    public void certEmail(Email email, String code) {
        List<Email> selectedEmails = certRepository.findEmailByCode(code);
        if (!selectedEmails.contains(email)){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("인증번호가 잘못되었습니다.")
                    .code(401)
                    .method(HttpMethod.GET)
                    .build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public void updateTeacherNickname(UpdateTeacherDto dto) {
        teacherRepository.updateNickname(dto);
    }

    @Override
    public void updateTeacherPassword(UpdatePasswordDto dto) {
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
