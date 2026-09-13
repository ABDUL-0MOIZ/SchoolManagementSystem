package com.example.school_management_system.Model;

import com.mongodb.client.model.Collation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Document(collection = "refreshToken")
@Data
@Component
public class RefreshToken {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String token;
    private Instant expireyDate;
}
