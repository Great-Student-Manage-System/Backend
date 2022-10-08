package com.example.demo.repository;

import com.example.demo.model.Record;
import com.example.demo.model.RecordSelectDto;
import com.example.demo.model.dto.request.UpdateRecordDto;

import java.util.List;

public interface RecordRepository {
    void save (Record record);
    List<Record> findByRecordSelectDto(RecordSelectDto dto);
    List<Record> findByExamId(int examId);
    void update (UpdateRecordDto dto);
}