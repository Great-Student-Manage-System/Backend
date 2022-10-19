package com.example.demo.repository;

import com.example.demo.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CertRepositoryImpl implements CertRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean canJoin(Email email) {
        String sql = "select count(*) from teacher where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, email.toString());
        if(count==null)return false;
        return count == 1;
    }

    @Override
    public void saveEmailCod(Email email, String code) {
        String sql = "select count(*) from `email-code` where email =?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, email.toString());
        if(count==null){
            sql = "insert into `email-code`(email,code,false) values(?,?)";
            jdbcTemplate.update(sql,email.toString(),code);
        }else {
            if(count==1){
                sql = "update `email-code` set code =? where email =?";
                jdbcTemplate.update(sql,code,email.toString());
            }else{
                sql = "insert into `email-code` values(?,?,false)";
                jdbcTemplate.update(sql,email.toString(),code);
            }
        }
    }

    @Override
    public List<Email> findEmailByCode(String code) {
        String sql = "select email from `email-code` where code = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Email(rs.getString("email")),code);
    }

    @Override
    public void certEmail(Email email) {
        String sql = "update `email-code` set verify = true where email =?";
        jdbcTemplate.update(sql,email.toString());
    }

    @Override
    public boolean isVerified(Email email) {
        String sql = "select verify from `email-code` where email = ?";
        return jdbcTemplate.queryForObject(sql,Boolean.class,email.toString());
    }
}
