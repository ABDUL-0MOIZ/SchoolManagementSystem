package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentAttandanceRequest {
    @NotBlank
private String enrolGradeId;
    @NotBlank
private String status;
}
