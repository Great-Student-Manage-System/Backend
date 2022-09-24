package com.example.demo.repository;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.Teacher;
import com.example.demo.model.UpdtatePasswordDto;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    void save(Teacher teacher);
    Optional<Teacher> findById(int id);
    Optional<Teacher> findByNickName(String nickname);
    void updateNickname(int id, String nickname);
    void updatePassword(UpdtatePasswordDto dto);
    Optional<Teacher> findByEmailPassword(Email email, Password password);
}
