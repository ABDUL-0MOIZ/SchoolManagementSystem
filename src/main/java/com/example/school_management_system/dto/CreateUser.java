package com.example.school_management_system.dto;

import com.example.school_management_system.Model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
@Data
public class CreateUser {
@NotBlank(message = "UserName is required")
    String userName;

    @Indexed(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide the right email")
    String email;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain uppercase, lowercase, number and special character"
    )
    String password;

    List<Role> role;}
