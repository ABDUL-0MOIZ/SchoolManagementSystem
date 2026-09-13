package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "Fee_Challan")
@Data
public class FeeChallan {
    @Id
    private String id;
    @Indexed
    private String studentId;
    @Indexed
    private String academicYearId;
    private Date date;
    private Date dueDate;
    private double amount;
    private String status;
}
