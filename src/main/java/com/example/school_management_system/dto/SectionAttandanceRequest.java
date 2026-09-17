package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SectionAttandanceRequest {
    @NotBlank
    private String sectionId;
    @NotBlank
    private String academicYearId;
    @NotBlank
    private LocalDate date;
    @NotEmpty
    private List<StudentAttandanceRequest> students;
}