package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.model.RecordSelectDto;
import com.example.demo.model.RecordUpdateDto;
import com.example.demo.model.Subject;
import com.example.demo.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
        recordRepository.save(record);
    }

    @Override
    public void updateStudentRecord(RecordUpdateDto recordUpdateDto) {
        try{
            recordRepository.update(recordUpdateDto);
        }catch(Exception e){

        }
    }
}
