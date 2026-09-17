package com.example.school_management_system.controller;

import com.example.school_management_system.dto.Parent.CreateParentReq;
import com.example.school_management_system.dto.Parent.ParentResponse;
import com.example.school_management_system.service.EmailService;
import com.example.school_management_system.service.FileService;
import com.example.school_management_system.service.ParentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/parent")
@Validated
public class ParentController {
     private final ParentService parentService;
     @Autowired
     FileService fileService;
     @Autowired
     EmailService emailService;
     public ParentController(ParentService parentService){
        this.parentService=parentService;
     }
     @PreAuthorize("hasAuthority('PARENT_CREATE')")
      @PostMapping("/createparent")
     public ResponseEntity<?> createParent(@RequestBody @Valid CreateParentReq createParentReq){
         parentService.createParent(createParentReq);
         return new ResponseEntity<>("Succesfully Created", HttpStatus.CREATED);
     }
    @GetMapping("/find/{email}")
    public ResponseEntity<?> findParentByEmail(@PathVariable @Email String email){
       ParentResponse p=parentService.getParent(email);
       return  new ResponseEntity<>(p,HttpStatus.OK);
    }
    @PostMapping("/uploud")
    public void uploadImage(@RequestParam MultipartFile file) {
        try {


            fileService.saveFile(file);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    @PostMapping("/send")
    public void sendEmail(@RequestParam @Email(message = "Please provide the right email") String to,@RequestParam String sub,@RequestParam String text){
 emailService.sendMail(to,sub,text);
    }
}
