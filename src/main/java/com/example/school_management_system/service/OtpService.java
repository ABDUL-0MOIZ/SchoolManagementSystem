package com.example.school_management_system.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {
private final RedisService redisService;
private  final EmailService emailService;
public OtpService(RedisService redisService,EmailService emailService)

{
this.emailService=emailService;
this.redisService=redisService;
}
public void junrateotp(String email){
    String otp=String.valueOf(
            100000+(new Random().nextInt(900000))
    );
    String key="otp:"+email;
    //3 minute expiree
    redisService.save(key,otp,180);
emailService.sendMail(email,
        "OTP to Reset Password From SMS",
        "Hi Please Dont Share any persnol Detail. Your Otp to reset Password is: "+otp+" this is expired in 3 minute");

}
public boolean verifyotp(String email,String otp){
   String saveOtp= redisService.getValue("otp:"+email);
if(saveOtp==null){
    throw new RuntimeException("otp is expired ");
}
if(saveOtp.equals(otp)){
    return  true;
}
return false;
}

}
