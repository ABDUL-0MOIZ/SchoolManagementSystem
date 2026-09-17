package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SubjectTeacherRequest;
import com.example.school_management_system.dto.SubjectTeacherResponse;
import com.example.school_management_system.service.SubjectTeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/subjectTeacher")
public class SubjectTeacherController {
    public final SubjectTeacherService subjectTeacherService;
    public SubjectTeacherController(SubjectTeacherService subjectTeacherService){
        this.subjectTeacherService=subjectTeacherService;
    }

    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid SubjectTeacherRequest request){
        subjectTeacherService.create(request);
    return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam String id, @RequestBody @Valid SubjectTeacherRequest request){
        subjectTeacherService.update(id,request);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable @NotBlank String id){
        SubjectTeacherResponse response=subjectTeacherService.findById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotBlank String id){
        subjectTeacherService.delete(id);
  return new ResponseEntity<>("Succesfully Deleted",HttpStatus.OK);
    }
}
