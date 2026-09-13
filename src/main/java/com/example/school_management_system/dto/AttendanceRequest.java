package com.example.school_management_system.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
public class AttendanceRequest {
    @NotNull
    private String enroledGradeId;
    @NotNull
    private LocalDate date;
    @NotNull
    private String status;
}
