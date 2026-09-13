package com.example.school_management_system.dto.Parent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
@Data
public class CreateParentReq {
@NotBlank
    String email;
    @Pattern(regexp = "^[0-9]{5}-[0-9]{7}-[0-9]{1}$", message = "Invalid CNIC format (e.g. 35202-1234567-1)")
@NotBlank
    String cnic;
    String address;
    @NotBlank
    String phno;

    List<String> studentid;

}
