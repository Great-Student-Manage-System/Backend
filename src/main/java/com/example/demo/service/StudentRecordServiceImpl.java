package com.example.demo.service;

import com.example.demo.exception.SystemException;
import com.example.demo.model.dto.response.ErrorMessage;
import com.example.demo.model.dto.request.AddRecordDto;
import com.example.demo.model.dto.request.UpdateRecordDto;
import com.example.demo.model.dto.response.SelectRecordResponseDto;
import com.example.demo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentRecordServiceImpl implements StudentRecordService{
    @Autowired
    private RecordRepository recordRepository;
    @Override
    public List<SelectRecordResponseDto> getStudentRecords(int studentId, String subject, LocalDate year) {
        return recordRepository.findByRecordSelectDto(studentId, subject, year.getYear());
    }

    @Override
    public void addStudentRecord(AddRecordDto record) {
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
    public void updateStudentRecord(UpdateRecordDto updateRecordDto) {
        try{
            recordRepository.update(updateRecordDto);
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