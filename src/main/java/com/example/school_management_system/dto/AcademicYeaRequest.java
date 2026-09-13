package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class AcademicYeaRequest {
    @NotBlank
    private String label;
    @NotBlank
    private Date start_date;
    @NotBlank
    private Date end_date;
    @NotBlank
    private Boolean statu;}
