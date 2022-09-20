package com.example.demo.repository;

import com.example.demo.model.Email;

public interface CertRepository {
    boolean canJoin(Email email);
    void saveEmailCod(Email email, String code);
    Email findEmailByCode(String code);

}
