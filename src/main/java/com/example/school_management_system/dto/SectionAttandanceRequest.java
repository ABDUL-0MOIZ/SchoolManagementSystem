package com.example.school_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SectionAttandanceRequest {
    private String sectionId;
    private String academicYearId;
    private LocalDate date;
    private List<StudentAttandanceRequest> students;
}