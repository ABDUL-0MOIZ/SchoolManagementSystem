package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EnroledClass")
@Data
public class EnrolGrade {
    @Id
   private String id;
    @Indexed
   private String sectionId;
   @Indexed
    private String studentId;
   private String academicYearId;
   private Boolean Status;
}
