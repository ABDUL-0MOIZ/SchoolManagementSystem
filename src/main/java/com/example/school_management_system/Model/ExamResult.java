package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "Exam_Result")
public class ExamResult {
    @Id
    private String id;
    @Indexed
    private String enrolmentId;
    @Indexed
    private  String examScheduleId;
    private  int totalMarks;
    private double obtainMarks;
    private char grade;
    private String remarks;
}
