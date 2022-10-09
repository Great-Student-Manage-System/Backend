package com.example.demo.repository;

import com.example.demo.model.Email;
import com.example.demo.model.Password;
import com.example.demo.model.dto.request.JoinDto;
import com.example.demo.model.dto.request.UpdatePasswordDto;
import com.example.demo.model.dto.request.UpdateTeacherDto;
import com.example.demo.model.dto.response.SelectTeacherResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(JoinDto teacher) {
        String sql = "insert into teacher(email,subject,nickName,password) values(?,?,?,?)";
        jdbcTemplate.update(sql,teacher.getEmail(),teacher.getSubject(),teacher.getNickName(),teacher.getPassword());
    }

    @Override
    public Optional<SelectTeacherResponseDto> findById(int id) {
        String sql = "select * from teacher where id =?";
        SelectTeacherResponseDto result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> SelectTeacherResponseDto.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .nickName(rs.getString("nickName"))
                .subject(rs.getString("subject"))
                .build(),id);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<SelectTeacherResponseDto> findByNickName(String nickname) {
        String sql = "select * from teacher where nickName =?";
        SelectTeacherResponseDto result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> SelectTeacherResponseDto.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .nickName(rs.getString("nickName"))
                .subject(rs.getString("subject"))
                .build(),nickname);
        return Optional.ofNullable(result);
    }

    @Override
    public void updateNickname(UpdateTeacherDto dto) {
        String sql = "update teacher set nickName=? where id = ?";
        jdbcTemplate.update(sql,dto.getNickName(),dto.getId());
    }

    @Override
    public void updatePassword(UpdatePasswordDto dto) {
        String sql = "update teacher set password=sha2(?,256) where id = ?";
        jdbcTemplate.update(sql,dto.getPassword(),dto.getId());
    }

    @Override
    public Optional<SelectTeacherResponseDto> findByEmailPassword(Email email, Password password) {
        String sql = "select * from teacher where email =? and password = ?";
        SelectTeacherResponseDto result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> SelectTeacherResponseDto.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .nickName(rs.getString("nickName"))
                .subject(rs.getString("subject"))
                .build(),email.toString(),password.toString());
        return Optional.ofNullable(result);
    }
}
