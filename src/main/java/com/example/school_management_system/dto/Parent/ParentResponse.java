package com.example.school_management_system.dto.Parent;

import com.example.school_management_system.dto.UserResponse;
import lombok.Data;

import java.util.List;
@Data
public class ParentResponse {
    UserResponse userResponse;
    String cnic;
    String address;
    String phno;
    List<ParentStudentsResponse> studentResponses;
}
