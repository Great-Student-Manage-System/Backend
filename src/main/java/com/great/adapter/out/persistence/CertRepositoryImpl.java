package com.great.adapter.out.persistence;

import com.great.application.port.out.CertRepository;
import com.great.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
class CertRepositoryImpl implements CertRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean canJoin(Email email) {
        String sql = "select count(*) from teacher where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, email.toString());
        if(count==null)return false;
        return count != 1;
    }

    @Override
    public void saveEmailCod(Email email, String code) {
        String sql = "select count(*) from `email-code` where email =?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class, email.toString());
        if(count==null){
            sql = "insert into `email-code`(email,code) values(?,?)";
            jdbcTemplate.update(sql,email.toString(),code);
        }else {
            if(count==1){
                sql = "update `email-code` set code =? where email =?";
                jdbcTemplate.update(sql,code,email.toString());
            }else{
                sql = "insert into `email-code`(email,code) values(?,?)";
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
        String sql = "update `email-code` set verify = true where email = ?";
        jdbcTemplate.update(sql,email.toString());
    }

    @Override
    public boolean isCert(Email email) {
        String sql = "select count(verify) from `email-code` where email = ? and verify = true";
        Integer result = jdbcTemplate.queryForObject(sql,Integer.class,email.toString());
        try {
            return result == 1;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean checkNickName(String nickName) {
        String sql = "select count(nickName) from teacher where nickName = ?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,nickName);
        if(count==null || count != 0){
            return false;
        }else {
            return true;
        }
    }
}
