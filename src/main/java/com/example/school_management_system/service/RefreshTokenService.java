package com.example.school_management_system.service;

import com.example.school_management_system.Model.RefreshToken;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepo;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepo) {
        this.refreshTokenRepo = refreshTokenRepo;
    }
    public RefreshToken createRefreshToke(User u){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setUserId(u.getId());
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpireyDate(Instant.now().plusSeconds(7*24*60*60));
        return refreshTokenRepo.save(refreshToken);
    }
    public RefreshToken findByToken(String token) {
        return refreshTokenRepo .findByToken(token) .orElseThrow(() ->
                new RuntimeException("Refresh Token not found"));
    }
    public RefreshToken checkExpire(RefreshToken refreshToken){
        if(refreshToken.getExpireyDate().isBefore(Instant.now())){
            refreshTokenRepo.deleteByUserId(refreshToken.getUserId());
            throw new RuntimeException("Refresh Token expired");

        }
        return refreshToken;

    }
    public void logout(String refreshToken){
        refreshTokenRepo.deleteByToken(refreshToken);
    }
}
