package com.example.demo.service;

import com.example.demo.model.Record;
import com.example.demo.model.Subject;

import java.util.Date;
import java.util.List;

public interface StudentRecordService {
    List<Record> getStudentRecords(int studentId, Subject subject, Date year);
    void addStudentRecord(Record record);
    void updateStudentRecord(Record record);
}
