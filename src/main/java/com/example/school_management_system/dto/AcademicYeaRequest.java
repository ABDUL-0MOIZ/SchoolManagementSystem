package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class AcademicYeaRequest {
    @NotBlank
    private String label;
    private Date start_date;
    private Date end_date;

    private Boolean status;}
