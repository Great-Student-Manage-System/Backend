package com.great.adapter.out.persistence;

import com.great.application.port.out.TeacherRepository;
import com.great.model.Email;
import com.great.model.Password;
import com.great.model.dto.request.ChangePasswordDto;
import com.great.model.dto.request.JoinDto;
import com.great.model.dto.request.ChangeTeacherDto;
import com.great.model.dto.response.SelectTeacherResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(JoinDto teacher) {
        String sql = "insert into teacher(email,subject,nickName,password) values(?,?,?,sha2(?,256))";
        jdbcTemplate.update(sql, teacher.getEmail(), teacher.getSubject(), teacher.getNickName(), teacher.getPassword());
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
    public void updateNickname(ChangeTeacherDto dto) {
        String sql = "update teacher set nickName=? where id = ?";
        jdbcTemplate.update(sql,dto.getNickName(),dto.getId());
    }

    @Override
    public void updatePassword(ChangePasswordDto dto) {
        String sql = "update teacher set password=sha2(?,256) where id = ?";
        jdbcTemplate.update(sql,dto.getNewPassword(),dto.getId());
    }

    @Override
    public Optional<SelectTeacherResponseDto> findByEmailPassword(Email email, Password password) {
        String sql = "select * from teacher where email =? and password = sha2(?,256)";
        List<SelectTeacherResponseDto> results = jdbcTemplate.query(sql, (rs, rowNum) -> SelectTeacherResponseDto.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .nickName(rs.getString("nickName"))
                .subject(rs.getString("subject"))
                .build(),email.toString(),password.toString());
        SelectTeacherResponseDto result = results.isEmpty()?null:results.get(0);
        return Optional.ofNullable(result);
    }
}
