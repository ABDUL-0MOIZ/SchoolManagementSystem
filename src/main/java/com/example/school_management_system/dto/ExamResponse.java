package com.example.school_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ExamResponse {
    String id;
    String name;
    String type;
    AcademicYearResponse academicYearResponse;
    LocalDate startDate;
    LocalDate endDate;
}
