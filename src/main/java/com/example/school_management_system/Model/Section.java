package com.example.school_management_system.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "section")
@Data
public class Section {
    @Id
  private String id;
  private String name;
  @Indexed
  private String gradeId;
  private String romeNo;
}
