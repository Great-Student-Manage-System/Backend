package com.great.application.port.in;

import com.great.model.dto.response.SelectExamResponseDto;
import com.great.model.dto.response.SelectExamsResponseDto;

import java.time.LocalDate;

/**
 * 시험정보를 조회하는 역할을 하는 Service 계층의 인터페이스 입니다.
 */
public interface ExamService {
    /**
     * 특정 연도의 시험 목록을 조회합니다.
     * @param year 조회하고자 하는 연도
     * @return
     */
    SelectExamsResponseDto getExams(int teacherId,LocalDate year);

    /**
     * 특정 시험의 정보를 조회합니다
     * @param examId 시험의 고유 아이디
     * @return
     */
    SelectExamResponseDto getExam(int examId);
}
