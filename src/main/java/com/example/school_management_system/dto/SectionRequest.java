package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
@Data
public class SectionRequest {
    @NotBlank
    private String name;
   @NotBlank
    private String gradeId;
   @NotBlank
   private String romeNo;
}
