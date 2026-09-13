package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubjectTeacher")
@Data
public class SubjectTeacher {
    @Id
   private String id;
   @Indexed
    private String sectionId;
   @Indexed
   private String subjectId;
   @Indexed
   private String teacherId;
   private String academicYearId;

}
