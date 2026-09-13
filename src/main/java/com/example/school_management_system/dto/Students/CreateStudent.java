package com.example.school_management_system.dto.Students;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateStudent {
    @NotBlank
    private String rollNo;
    @NotBlank
    private String userId;
    private String parentId;
   @NotBlank
    String gender;
   @NotBlank
    String dob;
}
