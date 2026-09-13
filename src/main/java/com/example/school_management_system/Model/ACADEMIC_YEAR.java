package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;

@Document(collection = "Academic_Year")
@Data
@Component
public class ACADEMIC_YEAR {
    @Id
    private String id;
    private String label;
    private Date start_date;
    private Date end_date;
    private Boolean statu;


}
