package com.example.school_management_system.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.PropertyKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Service
public class JwtService {
    @Value("${security_Key}")
    private  String secret_Key;
    public String junrateToken(UserDetails userDetails){
        return Jwts.builder().subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new java.util.Date(
                        System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSecertKey())
                .compact();
    }
    public SecretKey getSecertKey(){
        return Keys.hmacShaKeyFor(secret_Key.getBytes(StandardCharsets.UTF_8));
    }
    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(getSecertKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean ValidateToken(String token,UserDetails userDetails){
       String email=extractEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
       return getExpireDate(token).before(new Date());
}
    public Date getExpireDate(String token){
        return Jwts.parser().verifyWith(getSecertKey()).build()
                .parseSignedClaims(token).getPayload()
                .getExpiration();
    }
}
