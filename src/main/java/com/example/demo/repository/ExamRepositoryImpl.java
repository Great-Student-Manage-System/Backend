package com.example.demo.repository;

import com.example.demo.model.dto.request.AddExamDto;
import com.example.demo.model.dto.request.UpdateExamDto;
import com.example.demo.model.dto.response.ExamDto;
import com.example.demo.model.dto.response.SelectExamResponseDto;
import com.example.demo.model.dto.response.SelectExamsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public class ExamRepositoryImpl implements ExamRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(AddExamDto dto) {
        String sql = "insert into exam(name,examDate,gradeCut,subject) values(?,?,?,?)";
        jdbcTemplate.update(sql,dto.getName(), dto.getExamDate() ,dto.getGradeCut(),dto.getSubject());
    }

    @Override
    public Optional<SelectExamResponseDto> findById(int examId) {
        String sql = "select gradeCut from exam where id = ?";
        SelectExamResponseDto examResponseDto = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            String gradeCut = rs.getString("gradeCut");
            String[] grades = gradeCut.split(",");
            int[] arr = new int[grades.length];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(grades[i]);
            }
            return new SelectExamResponseDto(arr);
        },examId);
        return Optional.of(examResponseDto);
    }

    @Override
    public SelectExamsResponseDto findByYear(LocalDate year) {
        String sql = "select * from exam where Year(examDate) = ?";
        List<ExamDto> result = jdbcTemplate.query(sql, new RowMapper<ExamDto>() {
            @Override
            public ExamDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ExamDto.builder()
                        .examId(rs.getInt("id"))
                        .schoolYear(rs.getInt("schoolYear"))
                        .examName(rs.getString("name"))
                        .subject(rs.getString("subject")).build();
            }
        },year.getYear());
        return new SelectExamsResponseDto(result);
    }

    @Override
    public void update(UpdateExamDto dto) {
        String sql = "update exam set name =?,examDate=?,gradeCut=?,subject=? where id =?";
        jdbcTemplate.update(sql,dto.getName(),dto.getExamDate(),dto.getGradeCut(),dto.getSubject(),dto.getExamId());
    }
}
