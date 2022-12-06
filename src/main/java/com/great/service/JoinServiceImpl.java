package com.great.service;

import com.great.exception.SystemException;
import com.great.model.Email;
import com.great.model.Password;
import com.great.model.dto.request.JoinDto;
import com.great.model.dto.response.ErrorMessage;
import com.great.repository.CertRepository;
import com.great.repository.StudentRepository;
import com.great.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void checkNickName(String nickName) {
        if(!certRepository.checkNickName(nickName)){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("이미 사용중인 닉네임입니다.")
                    .code(409)
                    .build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public String createEmailCode(Email email) {
        if(certRepository.canJoin(email)) {
            Random random = new Random();
            int code = random.nextInt(900000) + 100000;
            certRepository.saveEmailCod(email, code + "");
            return code + "";
        }else {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("이미 사용중인 이메일입니다.")
                    .code(409)
                    .build();
            throw new SystemException(errorMessage);
        }
    }
    @Override
    public void certEmail(Email email, String code) {
        List<Email> selectedEmails = certRepository.findEmailByCode(code);
        if (!selectedEmails.contains(email)){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .message("인증번호가 잘못되었습니다.")
                    .code(401)
                    .build();
            throw new SystemException(errorMessage);
        }else {
            certRepository.certEmail(email);
        }
    }

    @Override
    public void join(JoinDto joinDto) {
        Email email;
        Password password;
        try {
            email = new Email(joinDto.getEmail());
            password = new Password(joinDto.getPassword());
        }catch (IllegalArgumentException e){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code(400)
                    .message(e.getMessage()).build();
            throw new SystemException(errorMessage);
        }
        if(!certRepository.checkNickName(joinDto.getNickName())){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code(409)
                    .message("이미 사용중인 닉네임입니다.").build();
            throw new SystemException(errorMessage);
        }
        if(certRepository.canJoin(email)){
            if (certRepository.isCert(email)){
                teacherRepository.save(joinDto);
            }else{
                ErrorMessage errorMessage = ErrorMessage.builder()
                        .code(401)
                        .message("인증되지 않은 이메일입니다.").build();
                throw new SystemException(errorMessage);
            }
        }else {
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code(409)
                    .message("이미 사용중인 이메일입니다.").build();
            throw new SystemException(errorMessage);
        }
    }
}
