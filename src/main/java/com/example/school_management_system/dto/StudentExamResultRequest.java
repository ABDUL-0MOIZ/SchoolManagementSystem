package com.example.school_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class StudentExamResultRequest {
@NotBlank
    private String enrolmentId;
@Max(value = 100)
private int totalMarks;
@Min(value = 0)
@Max(value = 100)
private int obtainMarks;
@NotBlank
@Size(max = 1)
private char grade;

    private String remarks;
}