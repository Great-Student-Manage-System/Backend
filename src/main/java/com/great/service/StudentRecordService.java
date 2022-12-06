package com.great.service;

import com.great.model.dto.request.AddRecordDto;
import com.great.model.dto.request.ChangeRecordDto;
import com.great.model.dto.response.SelectRecordResponseDto;

import java.time.LocalDate;
import java.util.List;

/**
 * 학생의 성적을 다루는 Service 계층의 인터페이스입니다.
 */
public interface StudentRecordService {
    /**
     * 특정 학생의 특정 년도의 특정 과목의 성적을 조회합니다.
     * @param studentId 학생의 고유 아이디
     * @param subject 과목이름
     * @param year 년도
     * @return 성적리스트
     */
    List<SelectRecordResponseDto> getStudentRecords(int studentId, String subject, LocalDate year);

    /**
     * 학생의 성적을 추가합니다.
     * @param record 성적정보
     */
    void addStudentRecord(AddRecordDto record);

    /**
     * 학생의 성적을 수정합니다.
     * @param changeRecordDto 성적정보
     */
    void changeStudentRecord(ChangeRecordDto changeRecordDto);
    void deleteStudentRecord(int recordId);
}
