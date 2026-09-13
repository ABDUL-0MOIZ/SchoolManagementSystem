package com.example.school_management_system.dto;

import lombok.Data;

@Data
public class StudentExamResultRequest {

    private String enrolmentId;
    private int totalMarks;
    private int obtainMarks;
    private char grade;
    private String remarks;
}