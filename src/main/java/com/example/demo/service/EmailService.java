package com.example.demo.service;

import com.example.demo.model.Email;

public interface EmailService {
    void sendEmail(Email email, String content);
}
