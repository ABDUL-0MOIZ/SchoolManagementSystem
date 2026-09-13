package com.example.school_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ExamResultResponse {
    private ExamScheduleResponse examScheduleResponse;
    private EnrolGradeResponse enrolGradeResponse;

    private int  totalMarks;

    private double obtainMarks;
    private char grade;
    private String Remarks;
}
