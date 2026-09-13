package com.example.school_management_system.service;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }
    public void sendMail(String to,String subject,String text){
        SimpleMailMessage msg=new SimpleMailMessage();
        
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);
    }
}
