package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.Date;
@Data
public class ExamScheduleRequest {
    @NotBlank
    private String examId;
    @NotBlank
    private  String subjectId;
    @NotBlank
    private String sectionID;

    private LocalDate startDate;
    private String startTime;
    private String endTime;
}
