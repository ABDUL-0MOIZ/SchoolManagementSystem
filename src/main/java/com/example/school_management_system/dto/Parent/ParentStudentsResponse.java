package com.example.school_management_system.dto.Parent;

import com.example.school_management_system.dto.UserResponse;
import lombok.Data;

@Data
public class ParentStudentsResponse {
    private String rolno;

    private UserResponse user;
     String gender;
    String dob;
}
