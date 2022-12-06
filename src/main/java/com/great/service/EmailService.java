package com.great.service;

import com.great.model.Email;

public interface EmailService {
    void sendEmail(Email email, String content);
}
