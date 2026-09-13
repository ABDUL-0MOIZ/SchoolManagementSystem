package com.example.school_management_system.dto;

import lombok.Data;

@Data
public class SubjectResponse {

    private String id;
    private  GradeResponse gradeResponse;
    private String name;
    private String code;
}
