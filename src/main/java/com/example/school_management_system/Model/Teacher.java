package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@Document(collection = "Teacher")
@Data
@Component
public class Teacher {
    @Id
   private String id;
    @Indexed(unique = true)
    private String employeeId;
    @Indexed
    private String userid;

    private String cnic;
    private String qulification;
    private Date joinDate;
}
