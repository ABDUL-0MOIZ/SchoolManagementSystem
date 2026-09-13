package com.example.school_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
@Data
public class SubjectTeacherRequest {
 @NotBlank
    private String sectionId;
 @NotBlank
    private String subjectId;
    @NotBlank
    private String teacherId;
    @NotBlank
    private String academicYearId;
}
