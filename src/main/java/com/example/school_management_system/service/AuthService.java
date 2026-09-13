package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.UserMapper;
import com.example.school_management_system.Model.RefreshToken;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.UserRepository;
import com.example.school_management_system.dto.CreateUser;
import com.example.school_management_system.dto.TokenResponse;
import com.example.school_management_system.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
public class AuthService {
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;
private final OtpService otpService;
private final RedisService redisService;
private final PasswordEncoder passwordEncoder;
private final UserService userService;
@Autowired
private  UserRepository userRepository;
private final RefreshTokenService refreshTokenService;
private final CustomUserDetailService customUserDetailService;
public AuthService(UserService userService,PasswordEncoder passwordEncoder, OtpService otpService,RedisService redisService,AuthenticationManager authenticationManager,CustomUserDetailService customUserDetailService,JwtService jwtService,RefreshTokenService refreshTokenService){
    this.authenticationManager=authenticationManager;
    this.jwtService=jwtService;
    this.userService=userService;
    this.passwordEncoder=passwordEncoder;
    this.otpService=otpService;
    this.redisService=redisService;
    this.customUserDetailService=customUserDetailService;
    this.refreshTokenService=refreshTokenService;
}
public TokenResponse Login(String username,String psd){
    Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,psd));
             UserDetails userDetails= (UserDetails) authentication.getPrincipal();
    assert userDetails != null;
    User u=userRepository.findByEmail(userDetails.getUsername());
             RefreshToken t=refreshTokenService.createRefreshToke(u);
             TokenResponse tokenResponse=new TokenResponse();
             tokenResponse.setRefreshToken(t.getToken());
             tokenResponse.setJwtToken(jwtService.junrateToken(userDetails));
             return  tokenResponse;

}
public TokenResponse refreshAccessToken(String refreshToken){
     RefreshToken rt=    refreshTokenService.findByToken(refreshToken);
             rt=  refreshTokenService.checkExpire(rt);

    User user = userRepository.findById(rt.getUserId()).orElseThrow(() ->
            new UserNotFoundException("User Not Found May Be Deleted!"));
    UserDetails userDetails=customUserDetailService.loadUserByUsername(user.getEmail());
    TokenResponse tokenResponse=new TokenResponse();
    tokenResponse.setJwtToken(jwtService.junrateToken(userDetails));
    tokenResponse.setRefreshToken(rt.getToken());
    return tokenResponse;
}
public void logout( String auth, String refreshToken){
    if(auth==null && !auth.startsWith("Bareer ")){
 throw new RuntimeException("Invalid Authorization header");
    }

    long expirationSeconds =
            (jwtService.getExpireDate(auth.substring(7)).getTime()
                    - System.currentTimeMillis()) / 1000;
    if (expirationSeconds > 0) {
        redisService.blacklistToken(
                auth.substring(7),
                expirationSeconds
        );
    }

    refreshTokenService.logout(refreshToken);
}
public void forgetPassword(String email){
    User user = userRepository.findByEmail(email);

    if (user == null) {
        throw new RuntimeException("User not found");
    }
    otpService.junrateotp(email);
}
public void resetPassword(String email,String otp,String psd){
    User user = userRepository.findByEmail(email);

    if (user == null) {
        throw new RuntimeException("User not found");
    }
  boolean verifyOtp=   otpService.verifyotp(email, otp);
    if(!verifyOtp){
        throw new RuntimeException("Otp does not match");
    }
    user.setPassword(passwordEncoder.encode(psd));
    userRepository.save(user);

}
public boolean verifyEmail(String email,String otp){
   boolean verifyOtp= otpService.verifyotp(email,otp);
if(!verifyOtp){
    return false;
}
          ObjectMapper objectMapper=new ObjectMapper();
                  String json= redisService.getValue("verify"+email);
                 CreateUser user= objectMapper.readValue(json, CreateUser.class);
                 user.setPassword(passwordEncoder.encode(user.getPassword()));
                 userService.createUser(UserMapper.createUser(user));
                 return true;
}
}
