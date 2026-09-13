package com.example.school_management_system.dto;

import com.example.school_management_system.Model.Grade;
import com.example.school_management_system.Repositroy.GradeRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GradeRequest {
  @NotBlank
    private String gradeName;
}
