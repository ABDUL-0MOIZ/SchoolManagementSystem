package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Class")
@Data
public class Grade {
   @Id
  private String id;
  private String className;
}
