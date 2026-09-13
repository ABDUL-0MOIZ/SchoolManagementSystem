package  com.example.school_management_system.dto;

import lombok.Data;

@Data
public class TokenResponse {
    String jwtToken;
    String RefreshToken;
}
