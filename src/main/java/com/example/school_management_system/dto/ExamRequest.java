package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExamRequest {
    @NotBlank
    private String academicYear;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private LocalDate startDate;
    private   LocalDate endDate;
}
