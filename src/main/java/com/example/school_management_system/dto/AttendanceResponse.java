package com.example.school_management_system.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AttendanceResponse {
 private EnrolGradeResponse enrolGradeResponse;
 private LocalDate Date;
 private String Status;
}
