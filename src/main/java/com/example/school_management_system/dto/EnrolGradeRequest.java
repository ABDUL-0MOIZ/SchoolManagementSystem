package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrolGradeRequest {

    @NotBlank(message = "Section ID is required")
    private String sectionId;

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotBlank(message = "Academic Year ID is required")
    private String academicYearId;

    @NotNull(message = "Status is required")
    private Boolean status;
}