package com.great.application.port.out;

import com.great.model.dto.request.AddRecordDto;
import com.great.model.dto.request.ChangeRecordDto;
import com.great.model.dto.response.SelectRecordResponseDto;

import java.util.List;

public interface RecordRepository {
    void save (AddRecordDto record);
    List<SelectRecordResponseDto> findByRecordSelectDto(int StudentId, String subject, int year);
    List<SelectRecordResponseDto> findByExamId(int examId);
    void update (ChangeRecordDto dto);
    void delete(int recordId);
}