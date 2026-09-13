package com.example.school_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ExamRequest {
    private String academicYear;
    private String name;
    private String type;
    private LocalDate startDate;
    private   LocalDate endDate;
}
