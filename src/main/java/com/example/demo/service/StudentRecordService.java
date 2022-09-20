package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.model.RecordUpdateDto;
import com.example.demo.model.Subject;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface StudentRecordService {
    List<Record> getStudentRecords(int studentId, String subject, LocalDate year);
    void addStudentRecord(Record record);
    void updateStudentRecord(RecordUpdateDto recordUpdateDto);
}
