package com.example.school_management_system.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "parents")
@Data
public class Parent {
    @Id
    private String id;
    @Indexed
    private String userId;

     private String cnic;

    private String address;

    private String phno;

     private List<String> studentIds = new ArrayList<>();
}