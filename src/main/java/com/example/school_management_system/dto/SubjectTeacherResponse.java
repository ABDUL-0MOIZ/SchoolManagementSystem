package com.example.school_management_system.dto;

import com.example.school_management_system.dto.Teacher.TeacherResponse;
import lombok.Data;

@Data
public class SubjectTeacherResponse {
    private SubjectResponse subjectResponse;
    private TeacherResponse teacherResponse;
    private SectionResponse sectionResponse;
    private AcademicYearResponse academicYearResponse;

}
