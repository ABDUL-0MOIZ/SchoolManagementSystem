package com.example.school_management_system.dto;

import com.example.school_management_system.dto.Students.StudentResponse;
import lombok.Data;

@Data
public class EnrolGradeResponse {

    private String id;
    private SectionResponse sectionResponse;
    private StudentResponse studentResponse;
    private AcademicYearResponse academicYearResponse;
    private Boolean status;
}