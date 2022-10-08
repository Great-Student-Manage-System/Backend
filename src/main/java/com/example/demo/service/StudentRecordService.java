package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.model.dto.request.UpdateRecordDto;

import java.time.LocalDate;
import java.util.List;

public interface StudentRecordService {
    List<Record> getStudentRecords(int studentId, String subject, LocalDate year);
    void addStudentRecord(Record record);
    void updateStudentRecord(UpdateRecordDto updateRecordDto);
}
