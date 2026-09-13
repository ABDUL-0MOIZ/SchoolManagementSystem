package com.example.school_management_system.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data

@Document(collection = "Users")
public class User{
    @Id
   private String id;
    private String userName;
    @Indexed(unique = true)
   private String email;
    private String password;
   private List<Role> role;

}
