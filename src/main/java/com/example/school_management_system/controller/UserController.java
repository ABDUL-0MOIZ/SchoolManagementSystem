package com.example.school_management_system.controller;

import com.example.school_management_system.Model.Permission;
import com.example.school_management_system.Model.Role;
import com.example.school_management_system.dto.CreateUser;
import com.example.school_management_system.dto.UserResponse;
import com.example.school_management_system.service.OtpService;
import com.example.school_management_system.service.RedisService;
import com.example.school_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RedisService redisService;
    private final OtpService otpService;

    public UserController(UserService userService,OtpService otpService,RedisService redisService)
    {
        this.otpService=otpService;
        this.redisService=redisService;
        this.userService=userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid  @RequestBody CreateUser user){
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(user);
        redisService.save("verify"+user.getEmail(),json,180);
        otpService.junrateotp(user.getEmail());

  return  new ResponseEntity<>("OTP is Send On Your Email and Otp is expire in 3 minutes go this link to Complete verifiation POST /auth/verify-email", HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping("/getAlluser")
    public ResponseEntity<?> getAllUser(){
     List<UserResponse> userResponseList=  userService.getUsers();
    return new ResponseEntity<>(userResponseList,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestParam String id,@RequestBody CreateUser user){
        userService.updateUser(id, user);
        return new ResponseEntity<>("Succesfully updated",HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('USER_DELETE')")
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id){
        userService.deleteUserByID(id);
        return new ResponseEntity<>("Succesfully deleted",HttpStatus.OK);
    }
}
