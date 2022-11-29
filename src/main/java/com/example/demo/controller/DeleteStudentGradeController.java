/*
package com.example.demo.controller;

import com.example.demo.model.dto.response.ResponseDto;
import com.example.demo.service.StudentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteStudentGradeController {
    @Autowired
    private StudentRecordService studentRecordService;

    @DeleteMapping("/api/students/exam/result/{recordId}")
    public ResponseEntity<ResponseDto<?>> addStudentRecord(@PathVariable int recordId){
            studentRecordService.deleteStudentRecord(recordId); //Todo 자신이 가르치는 학생이 아닌 사람의 성적을 삭제하지 못하도록 검증 필제
            return ResponseEntity.ok(ResponseDto.builder().code(200).response("성적이 삭제되었습니다.").build());
    }
}
*/
