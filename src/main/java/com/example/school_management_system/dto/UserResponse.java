package com.example.school_management_system.dto;
import com.example.school_management_system.Model.Role;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class UserResponse {


    String userName;
    String email;
    String password;
    List<Role> role;
}
