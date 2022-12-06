package com.great.application.port.in;

import com.great.model.Email;

public interface EmailService {
    void sendEmail(Email email, String content);
}
