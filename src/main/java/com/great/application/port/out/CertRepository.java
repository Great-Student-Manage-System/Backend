package com.great.application.port.out;

import com.great.model.Email;

import java.util.List;

public interface CertRepository {
    boolean canJoin(Email email);
    void saveEmailCod(Email email, String code);
    List<Email> findEmailByCode(String code);
    void certEmail(Email email);

    boolean isCert(Email email);

    boolean checkNickName(String nickName);

}
