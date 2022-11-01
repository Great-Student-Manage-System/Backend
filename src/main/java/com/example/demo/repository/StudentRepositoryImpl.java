package com.example.demo.repository;

import com.example.demo.model.dto.request.AddStudentDto;
import com.example.demo.model.dto.request.UpdateStudentDto;
import com.example.demo.model.dto.response.SelectStudentResponseDto;
import com.example.demo.model.dto.response.StudentWithExamScore;
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
    public List<StudentWithExamScore> findByTeacherAndExam(int teacherId, int examId) {
        String sql = "select * from student join record on record.student = student.id where record.exam = ? and student.teacher = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> StudentWithExamScore.builder()
                .studentId(rs.getInt("id"))
                .schoolYear(rs.getInt("grade"))
                .name(rs.getString("name"))
                .school(rs.getString("school"))
                .subjects(rs.getString("subjects"))
                .examScore(rs.getInt("score"))
                .build(), examId, teacherId);
    }

    @Override
    public int pageCountByTeacherAndMaxPage(int teacherId, int maxPage) {
        String sql ="select " +
                "case " +
                "when count(*)%? = 0 then count(*)/? " +
                "when count(*)%? != 0 then count(*)/? + 1 " +
                "when count(*) = 0 then 1 " +
                "end as count " +
                "from student " +
                "where teacher = ?";
        return jdbcTemplate.queryForObject(sql,Integer.class,maxPage,maxPage,maxPage,maxPage,teacherId);
    }

    @Override
    public void deleteStudent(int teacherId, int studentId) {
        String sql = "delete from student where teacher = ? and id = ?";
        jdbcTemplate.update(sql,teacherId,studentId);
    }

    @Override
    public void updateStudent(int teacherId, UpdateStudentDto student) {
        String sql = "update student set name =? where id = ?";
        if(student.getName()!=null){
            jdbcTemplate.update(sql,student.getName(),student.getId());
        }
        if(student.getSchool()!=null){
            sql = "update student set school =? where id = ?";
            jdbcTemplate.update(sql,student.getSchool(),student.getId());
        }
        if(student.getSubjects()!=null){
            sql = "update student set subjects =? where id = ?";
            jdbcTemplate.update(sql,student.getSubjects(),student.getId());
        }
        if (student.getSchoolYear()!=0){
            sql = "update student set grade =? where id = ?";
            jdbcTemplate.update(sql,student.getSchoolYear(),student.getId());
        }
    }

    private static class SelectStudentResponseDtoRowMapper<T extends SelectStudentResponseDto> implements RowMapper<T>{

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
