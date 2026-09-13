package com.example.school_management_system.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
@Data
public class ExamResultRequest {
 @NotBlank
    private String enrolmentId;
 @NotBlank
    private  String examScheduleId;
    @Max(value =100 )
 private  int totalMarks;
    @Max(value =100 )
   private double obtainMarks;
 @NotBlank
    private char grade;

    private String remarks;
}
