package com.example.demo.repository;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(int teacherId,AddStudentDto student) {
        String sql = "insert into student(name,grade,teacher,subjects,school,year) values(?,?,?,?,?,year(current_timestamp()))";
        jdbcTemplate.update(sql,student.getName(),student.getSchoolYear(),teacherId,student.getSubjects(),student.getSchool());
    }

    @Override
    public Optional<SelectStudentResponseDto> findById(int id) {
        String sql = "select * from student where id =?";
        SelectStudentResponseDto dto = jdbcTemplate.queryForObject(sql, new SelectStudentResponseDtoRowMapper<>() ,id);
        return Optional.of(dto);
    }

    @Override
    public List<SelectStudentResponseDto> findByName(String name) {
        return null;
    }

    @Override
    public List<SelectStudentResponseDto> findByGrade(int grade) {
        return null;
    }

    @Override
    public List<SelectStudentResponseDto> findBySubject(String subject) {
        return null;
    }

    @Override
    public List<SelectStudentResponseDto> findBySchool(String school) {
        return null;
    }

    @Override
    public List<SelectStudentResponseDto> findByTeacher(int teacherId, int page) {
        String sql = "select * from student where teacher = ? limit ?,?";
        return jdbcTemplate.query(sql,new SelectStudentResponseDtoRowMapper<>(), teacherId, (page-1)*10,10);
    }

    @Override
    public List<SelectStudentResponseDto> findByTeacherAndExam(int teacherId, int examId) {
        String sql = "select * from student join record on record.student = student.id where record.exam = ? and student.teacher = ?";
        return jdbcTemplate.query(sql,new SelectStudentResponseDtoRowMapper<>(), examId, teacherId);
    }

    private class SelectStudentResponseDtoRowMapper<T extends SelectStudentResponseDto> implements RowMapper<T>{

        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            return (T)SelectStudentResponseDto.builder()
                    .studentId(rs.getInt("id"))
                    .grade(rs.getInt("grade"))
                    .name(rs.getString("name"))
                    .school(rs.getString("school"))
                    .subjects(rs.getString("subjects"))
                    .build();
        }
    }
}
