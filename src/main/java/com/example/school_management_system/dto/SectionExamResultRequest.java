package com.example.school_management_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectionExamResultRequest {

    private String sectionId;
    private String yearId;
    private String examScheduleId;

    private List<StudentExamResultRequest> students;
}