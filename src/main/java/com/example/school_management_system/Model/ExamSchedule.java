package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "Exam_Schedule")
@Data
public class ExamSchedule {
    @Id
    private String id;
    @Indexed
    private String examId;
    @Indexed
    private  String subjectId;
   @Indexed
    private String sectionID;
    private LocalDate startDate;
    private String startTime;
    private String endTime;

}
