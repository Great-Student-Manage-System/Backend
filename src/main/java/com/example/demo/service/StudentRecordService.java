package com.example.demo.service;

import com.example.demo.model.dto.request.AddRecordDto;
import com.example.demo.model.dto.request.UpdateRecordDto;
import com.example.demo.model.dto.response.SelectRecordResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface StudentRecordService {
    List<SelectRecordResponseDto> getStudentRecords(int studentId, String subject, LocalDate year);
    void addStudentRecord(AddRecordDto record);
    void updateStudentRecord(UpdateRecordDto updateRecordDto);
}
