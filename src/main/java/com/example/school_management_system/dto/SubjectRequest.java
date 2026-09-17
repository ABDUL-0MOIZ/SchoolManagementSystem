package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequest {
    @NotBlank
    private String gradeId;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
