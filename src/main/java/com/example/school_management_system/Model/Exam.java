package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "exams")
@Data
public class Exam {
    @Id
    private String id;
    @Indexed
    private String academicYearId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String examType;
}