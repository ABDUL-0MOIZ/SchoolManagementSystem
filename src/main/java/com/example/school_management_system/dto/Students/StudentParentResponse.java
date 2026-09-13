package com.example.school_management_system.dto.Students;

import com.example.school_management_system.dto.UserResponse;
import lombok.Data;

@Data
public class StudentParentResponse {

    UserResponse userResponse;

    String cnic;
    String address;

    String phno;
}
