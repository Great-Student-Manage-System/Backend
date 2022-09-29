package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.*;
import com.example.demo.repository.RecordRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.ErrorManager;

@Service
public class StudentRecordServiceImpl implements StudentRecordService{

    private RecordRepository recordRepository;
    @Override
    public List<Record> getStudentRecords(int studentId, String subject, LocalDate year) {
        RecordSelectDto selectDto = new RecordSelectDto(studentId, subject, year);
        return recordRepository.findByRecordSelectDto(selectDto);
    }

    @Override
    public void addStudentRecord(Record record) {
        try{
            recordRepository.save(record);
        }catch (Exception e){
            //sql문에서 유니크를 중복으로 던지면 생기는 에러
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code(400)
                    .message("이미 해당 학생의 성적이 등록된 시험입니다.")
                    .build();
            throw new SystemException(errorMessage);
        }
    }

    @Override
    public void updateStudentRecord(RecordUpdateDto recordUpdateDto) {
        try{
            recordRepository.update(recordUpdateDto);
        }catch(Exception e){
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code(404)
                    .message("아직 등록된 적이 없는 시험입니다.")
                    .method(HttpMethod.PATCH)
                    .build();
            throw new SystemException(errorMessage);
        }
    }
}