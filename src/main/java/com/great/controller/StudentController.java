package com.great.controller;

import com.great.model.dto.request.AddRecordDto;
import com.great.model.dto.request.AddStudentDto;
import com.great.model.dto.request.ChangeStudentDto;
import com.great.model.dto.response.ResponseDto;
import com.great.model.dto.response.SelectStudentsResponseDto;
import com.great.model.dto.response.StudentWithExamScore;
import com.great.service.StudentRecordService;
import com.great.service.StudentService;
import com.great.token.AccessToken;
import com.great.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/api/students")
    public ResponseEntity<ResponseDto<?>> addStudent(@RequestHeader("Authorization") String accessTokenString, @RequestBody AddStudentDto dto){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.addStudent(teacherId,dto);
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("학생 추가에 성공했습니다").build());
    }

    @Autowired
    private StudentRecordService studentRecordService;

    @PostMapping("/api/students/exam/result")
    public ResponseEntity<ResponseDto<?>> addStudentRecord(@RequestBody AddRecordDto dto){
        studentRecordService.addStudentRecord(dto);
        return ResponseEntity.ok(ResponseDto.builder().code(201).response("성적이 추가되었습니다.").build());
    }


    @DeleteMapping("/api/students/{studentId}")
    public ResponseEntity<ResponseDto<?>> deleteStudent(@RequestHeader("Authorization") String accessTokenString,@PathVariable int studentId){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.deleteStudent(teacherId,studentId);
        return ResponseEntity.ok(ResponseDto.builder().response("학생을 삭제했습니다.").code(200).build());
        /** 메서드명 delete -> deleteStudent **/
    }

    @DeleteMapping("/api/students/exam/result/{recordId}")
    public ResponseEntity<ResponseDto<?>> deleteStudentRecord(@PathVariable int recordId){
        studentRecordService.deleteStudentRecord(recordId); //Todo 자신이 가르치는 학생이 아닌 사람의 성적을 삭제하지 못하도록 검증 필제
        return ResponseEntity.ok(ResponseDto.builder().code(200).response("성적이 삭제되었습니다.").build());
    }   /** 메서드명 addStudentRecord -> deleteStudentRecord **/

    @GetMapping("/api/students/{page}")
    public ResponseEntity<ResponseDto<?>> getStudentList(@RequestHeader("Authorization") String accessTokenString, @PathVariable int page){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        SelectStudentsResponseDto dto = studentService.getStudents(teacherId,page);

        ResponseDto<SelectStudentsResponseDto> responseDto = ResponseDto.<SelectStudentsResponseDto>builder()
                .code(200)
                .data(dto)
                .response("학생 목록 조회에 성공했습니다").build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/api/students/exam/{examId}")
    public ResponseEntity<ResponseDto<?>> getTakingExamStudentList(@RequestHeader("Authorization") String accessTokenString,@PathVariable int examId){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        List<StudentWithExamScore> result = studentService.getTakingExamStudents(teacherId,examId);
        ResponseDto<List<StudentWithExamScore>> responseDto = ResponseDto.<List<StudentWithExamScore>>builder()
                .data(result)
                .response("학생 목록 조회에 성공했습니다")
                .code(200).build();
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/api/students")
    public ResponseEntity<ResponseDto<?>> changeStudent(@RequestHeader("Authorization") String accessTokenString, @RequestBody ChangeStudentDto dto){
        AccessToken accessToken = new AccessToken(accessTokenString);
        int teacherId = tokenManager.getIdFromAccessToken(accessToken);
        studentService.changeStudent(teacherId,dto);
        return ResponseEntity.ok(ResponseDto.builder().response("학생을 수정했습니다.").code(200).build());
    }   /** 메서드명 update -> changeStudent **/
}
