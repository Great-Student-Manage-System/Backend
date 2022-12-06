package com.great.service;

import com.great.exception.SystemException;
import com.great.model.Email;
import com.great.model.dto.response.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(Email email, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.toString());
            message.setFrom("Great");
            message.setSubject("그레잇 이메일 인증 코드입니다.");
            message.setText(content);
            mailSender.send(message);
        }catch (Exception e){
            ErrorMessage errorMessage = ErrorMessage.builder().message("이메일 전송에 실패하였습니다.").code(500).build();
            throw new SystemException(errorMessage);
        }
    }
}
