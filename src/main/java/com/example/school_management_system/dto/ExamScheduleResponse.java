package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExamScheduleResponse {

    private String id;
    private ExamResponse examResponse;
    private  SubjectResponse subject;
    private SectionResponse section;

    private LocalDate startDate;
    private String startTime;
    private String endTime;
}
