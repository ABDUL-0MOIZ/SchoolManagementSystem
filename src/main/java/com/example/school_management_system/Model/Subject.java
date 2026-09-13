package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Subject")
@Data
public class Subject {

    @Id
  private String id;
  private String gradeId;
  private String name;
  private String code;
}
