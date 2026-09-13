package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
@Data
public class Attendance {
    @Id
    String id;
    @Indexed
    String enrolGradeID;
    @Indexed
    LocalDate date;
     String status;
}
