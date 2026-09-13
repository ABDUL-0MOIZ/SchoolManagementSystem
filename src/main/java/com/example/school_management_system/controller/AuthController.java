package com.example.school_management_system.controller;

import com.example.school_management_system.dto.LogInRequest;
import com.example.school_management_system.dto.TokenResponse;
import com.example.school_management_system.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
private final AuthService authService;
public AuthController(AuthService authService){
    this.authService=authService;
}
@PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LogInRequest logInRequest){
   TokenResponse token= authService.Login(logInRequest.getEmail(),logInRequest.getPassword());
    return new ResponseEntity<>(token, HttpStatus.OK);
}
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh( @RequestParam String refreshToken) {

    TokenResponse response = authService.refreshAccessToken(refreshToken);
    return ResponseEntity.ok(response);
}
@PostMapping("verify_email")
public ResponseEntity<?> verifyEmail(@RequestParam @Email(message = "Please provide the Right Email") String email, @RequestParam @Size(min = 6 ,max = 6,message = "6 digit otp") String otp){
   if( authService.verifyEmail(email,otp)){
       return new ResponseEntity<>("Successfully UserCreated",HttpStatus.CREATED);
   }
   return new ResponseEntity<>("otp is Expired Or wrong!",HttpStatus.BAD_REQUEST);

}
@PostMapping("/logout")
    public ResponseEntity<?> logout( @RequestHeader("Authorization") String authorization , String refreshToken){
    authService.logout(authorization,refreshToken);

         return new ResponseEntity<>("User is Succesfully logout",HttpStatus.OK);

}
@PostMapping("/forgetpassword")
    public ResponseEntity<?> forgetPassword(@RequestParam String email){
    authService.forgetPassword(email);
    return new ResponseEntity<>("Please check your email The otp is send there",HttpStatus.OK);

}
@PostMapping("resetpassword")
    public ResponseEntity<?> resetPassword(@RequestParam String email,@RequestParam String otp,@RequestParam String newPsd)
{
    authService.resetPassword(email,otp,newPsd);
    return new ResponseEntity<>("Your Password is Successfully updated",HttpStatus.OK);
}
}
