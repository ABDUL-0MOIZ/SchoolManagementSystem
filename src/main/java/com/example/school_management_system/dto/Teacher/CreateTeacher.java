package com.example.school_management_system.dto.Teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class CreateTeacher {
   @NotBlank
    @Indexed(unique = true)
    String employeeId;
   @Email
   @NotBlank
   String email;
   @NotBlank
   String userId;

   @NotBlank
   @Pattern(regexp = "^[0-9]{5}-[0-9]{7}-[0-9]{1}$", message = "Invalid CNIC format (e.g. 35202-1234567-1)")
   String cnic;
   @NotBlank
    String qulification;

}
