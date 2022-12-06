//package com.great;
//
//
//import com.great.application.port.in.*;
//import com.great.model.Email;
//import com.great.model.Password;
//import com.example.demo.model.dto.request.*;
//import com.example.demo.model.dto.response.*;
//import com.great.model.dto.response.*;
//import com.great.adapter.out.persistence.TeacherRepositoryImpl;
//import com.example.demo.service.*;
//import com.great.application.port.in.TokenManager;
//import com.great.application.service.token.Tokens;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.great.model.dto.request.*;
//import com.great.service.*;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ServiceTest {
//    @Autowired
//    EmailService emailService;
//    @Autowired
//    ExamService examService;
//    @Autowired
//    JoinService joinService;
//    @Autowired
//    TeacherService teacherService;
//    @Autowired
//    StudentRecordService studentRecordService;
//    @Autowired
//    StudentService studentService;
//    @Autowired
//    TokenManager tokenManager;
//    @Autowired
//    TeacherRepositoryImpl teacherRepository;
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    static AddExamDto addExamDto;
//    static AddRecordDto addRecordDto;
//    static AddStudentDto addStudentDto;
//    static JoinDto joinDto;
//    static LoginDto loginDto;
//    static RemakeAccessTokenDto remakeAccessTokenDto;
//    static ChangeExamDto changeExamDto;
//    static ChangePasswordDto changePasswordDto;
//    static ChangeRecordDto changeRecordDto;
//    static ChangeTeacherDto changeTeacherDto;
//
//    @Order(1)
//    @Test
//    void makeDto() throws JsonProcessingException {
//
//        addRecordDto = objectMapper.readValue("{\n" +
//                "  \"studentId\": 2,\n" +
//                "  \"examId\": 1,\n" +
//                "  \"subject\": \"물리1\",\n" +
//                "  \"examScore\": 40\n" +
//                "}",AddRecordDto.class);
//        addStudentDto = objectMapper.readValue("{\n" +
//                "  \"name\": \"김철수\",\n" +
//                "  \"schoolYear\": 3,\n" +
//                "  \"subjects\": \"물리1\",\n" +
//                "  \"school\": \"그레잇고교\"\n" +
//                "}",AddStudentDto.class);
//        joinDto = objectMapper.readValue("{\n" +
//                "  \"email\": \"test2@test.com\",\n" +
//                "  \"nickName\": \"선생님2\",\n" +
//                "  \"password\": \"test123!@#\",\n" +
//                "  \"subject\": \"과탐\"\n" +
//                "}",JoinDto.class);
//        loginDto = objectMapper.readValue("{\n" +
//                "  \"email\": \"test2@test.com\",\n" +
//                "  \"password\": \"test123!@#\"\n" +
//                "}",LoginDto.class);
//        remakeAccessTokenDto = objectMapper.readValue("{\n" +
//                "  \"accessToken\": \"\"\n" +
//                "}",RemakeAccessTokenDto.class);
//        changePasswordDto = objectMapper.readValue("{\n" +
//                "  \"id\": 2,\n" +
//                "  \"password\": \"test123!@#\",\n" +
//                "  \"newPassword\": \"test1234!@#\"\n" +
//                "}", ChangePasswordDto.class);
//        changeRecordDto = objectMapper.readValue("{\n" +
//                "  \"recordId\": 3,\n" +
//                "  \"examId\": 2,\n" +
//                "  \"examResult\": 20\n" +
//                "}", ChangeRecordDto.class);
//        changeTeacherDto = objectMapper.readValue("{\n" +
//                "  \"id\": 1,\n" +
//                "  \"nickName\": \"진짜선생님1\"\n" +
//                "}", ChangeTeacherDto.class);
//
////        addExamDto = objectMapper.readValue("{\n" +
////                "  \"name\": \"2022 9월모의고사\",\n" +
////                "  \"examDate\": \"2022-09-16\",\n" +
////                "  \"gradeCut\": [\n" +
////                "    48,44,40,36,32,28,24,12\n" +
////                "  ],\n" +
////                "  \"subject\": \"물리1\",\n" +
////                "  \"schoolYear\": 3\n" +
////                "}",AddExamDto.class);
////        updateExamDto = objectMapper.readValue("{\n" +
////                "  \"examId\": 3,\n" +
////                "  \"name\": \"2022 9월 모의고사\",\n" +
////                "  \"examDate\": \"2022-09-17\",\n" +
////                "  \"gradeCut\": [\n" +
////                "    48,44,40,36,32,28,24,20\n" +
////                "  ],\n" +
////                "  \"subject\": \"물리1\"\n" +
////                "}",UpdateExamDto.class);
//    }
//    @Order(2)
//    @Test
//    void addCertCode(){
//        joinService.createEmailCode(new Email(joinDto.getEmail()));
//        jdbcTemplate.update("update `email-code` set verify = true where email = ?",joinDto.getEmail());
//    }
//
//    @Order(2)
//    @Test
//    void join(){
//        joinService.join(joinDto);
//    }
//
//    @Order(3)
//    @Test
//    void login(){
//        Tokens tokens = tokenManager.makeTokens(new Email(loginDto.getEmail()),new Password(loginDto.getPassword()));
//        int realId = tokenManager.getIdFromAccessToken(tokens.getAccessToken());
//        Assertions.assertThat(realId).isEqualTo(2);
//    }
//
//    @Order(4)
//    @Test
//    void addStudent(){
//        studentService.addStudent(2,addStudentDto);
//    }
//
//    @Order(5)
//    @Test
//    void getStudents(){
//        SelectStudentsResponseDto result = studentService.getStudents(2,1);
//        String realName = result.getData().get(0).getName();
//        Assertions.assertThat(realName).isEqualTo(addStudentDto.getName());
//    }
//
//    @Order(6)
//    @Test
//    void addRecordTest(){
//        studentRecordService.addStudentRecord(addRecordDto);
//    }
//
//    @Order(7)
//    @Test
//    void getRecordTest(){
//        List<SelectRecordResponseDto> results = studentRecordService.getStudentRecords(2,addRecordDto.getSubject(),LocalDate.now());
//        SelectRecordResponseDto result = results.get(0);
//        int realExamId = result.getExamId();
//        int realScore = result.getScore();
//        Assertions.assertThat(realExamId).isEqualTo(addRecordDto.getExamId());
//        Assertions.assertThat(realScore).isEqualTo(addRecordDto.getExamScore());
//    }
//
//    @Order(8)
//    @Test
//    void updatePasswordTest(){
//        teacherService.changeTeacherPassword(changePasswordDto);
//    }
//
//    @Order(9)
//    @Test()
//    void login2(){
//        org.junit.jupiter.api.Assertions.assertThrows(Exception.class, ()->{
//            Tokens tokens = tokenManager.makeTokens(new Email(loginDto.getEmail()),new Password(loginDto.getPassword()));
//            int realId = tokenManager.getIdFromAccessToken(tokens.getAccessToken());
//        });
//    }
//
//    @Order(10)
//    @Test()
//    void login3(){
//        Tokens tokens = tokenManager.makeTokens(new Email(loginDto.getEmail()),new Password(changePasswordDto.getNewPassword()));
//        int realId = tokenManager.getIdFromAccessToken(tokens.getAccessToken());
//        Assertions.assertThat(realId).isEqualTo(2);
//    }
//
//    @Order(11)
//    @Test()
//    void  updateNickName(){
//        teacherService.changeTeacherNickname(changeTeacherDto);
//    }
//    @Order(12)
//    @Test()
//    void myInfoTest(){
//        SelectTeacherResponseDto result = teacherService.getTeacher(changeTeacherDto.getId());
//        String realName = result.getNickName();
//        Assertions.assertThat(realName).isEqualTo(changeTeacherDto.getNickName());
//    }
//    @Order(13)
//    @Test
//    void updateRecord(){
//        studentRecordService.changeStudentRecord(changeRecordDto);
//    }
//    @Order(14)
//    @Test
//    void getRecordTest2(){
//        int realScore = studentRecordService.getStudentRecords(2,addRecordDto.getSubject(),LocalDate.now()).get(0).getScore();
//        Assertions.assertThat(realScore).isEqualTo(changeRecordDto.getExamResult());
//    }
//
//    @Order(15)
//    @Test
//    void getExamTest(){
//        SelectExamsResponseDto results = examService.getExams(2,LocalDate.now());
//        List<ExamDto> list = results.getData();
//        int realExamCount = list.size();
//        Assertions.assertThat(realExamCount).isEqualTo(2);
//    }
//}