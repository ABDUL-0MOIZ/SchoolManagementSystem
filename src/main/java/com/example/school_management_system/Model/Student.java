package com.example.school_management_system.Model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
@Data
public class Student {
    @Id
    private  String id;
    @Indexed(unique = true)
    private String rollNo;
    @Indexed
    private String userId;
    @Indexed
    private String parentId;
    private String gender;
    private String dob;



}
