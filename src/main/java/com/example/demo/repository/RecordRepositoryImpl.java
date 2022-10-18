package com.example.demo.repository;

import com.example.demo.model.dto.request.AddRecordDto;
import com.example.demo.model.dto.request.UpdateRecordDto;
import com.example.demo.model.dto.response.SelectRecordResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class RecordRepositoryImpl implements RecordRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void save(AddRecordDto record) {
        String sql = "insert into record(student,detailSubject,exam,score) values(?,?,?,?)";
        jdbcTemplate.update(sql,record.getStudentId(),record.getSubject(),record.getExamId(),record.getExamScore());
    }

    @Override
    public List<SelectRecordResponseDto> findByRecordSelectDto(int studentId, String subject, int year) {
        String sql = "select * from record where student = ? and detailSubject =?" +
                "and exam in (select id from exam where Year(examDate) = ?)";
        return jdbcTemplate.query(sql, new SelectRecordResponseDtoRowMapper<>(),studentId,subject,year);
    }

    @Override
    public List<SelectRecordResponseDto> findByExamId(int examId) {
        String sql = "select * from record where exam = ?";
        return jdbcTemplate.query(sql, new SelectRecordResponseDtoRowMapper<>(),examId);
    }

    @Override
    public void update(UpdateRecordDto dto) {
        String sql = "update record set exam =?,score=? where id =?";
        if(dto.getExamId()==null){
            sql = sql.replace("exam =?,","");
            jdbcTemplate.update(sql,dto.getExamResult(),dto.getRecordId());
            return;
        }
        if(dto.getExamResult()==null){
            sql = sql.replace(",score=?","");
            jdbcTemplate.update(sql,dto.getExamId(),dto.getRecordId());
        }
    }

    private static class SelectRecordResponseDtoRowMapper<T extends SelectRecordResponseDto> implements RowMapper<T>{
        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            return (T) new SelectRecordResponseDto(rs.getInt("id"),rs.getInt("exam"),rs.getInt("score"));
        }
    }
}
