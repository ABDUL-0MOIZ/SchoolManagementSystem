package com.example.school_management_system.dto.Students;

import com.example.school_management_system.dto.UserResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentResponse {

    private String rolno;

    private UserResponse user;
   private StudentParentResponse parentResponse;
    String gender;
    String dob;

}
