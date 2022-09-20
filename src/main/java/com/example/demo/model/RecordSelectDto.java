package com.example.demo.model;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class RecordSelectDto {
    private int studentId;
    private String subject; // Todo 개선 필요
    private LocalDate year;
}
