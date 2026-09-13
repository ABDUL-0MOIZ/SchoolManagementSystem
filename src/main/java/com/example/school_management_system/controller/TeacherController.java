package com.example.school_management_system.controller;

import com.example.school_management_system.dto.Teacher.CreateTeacher;
import com.example.school_management_system.dto.Teacher.TeacherResponse;
import com.example.school_management_system.service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teacher")
@Validated
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PreAuthorize("hasAuthority('TEACHER_CREATE')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid CreateTeacher createTeacher){
        teacherService.CreateTeacher(createTeacher);
    return new ResponseEntity<>("Succesfully Created", HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('TEACHER_READ')")
    @PostMapping("/find")
    public ResponseEntity<?> findByEmployeeId(@RequestParam @NotBlank String empl){
        TeacherResponse teacherResponse=teacherService.getTeacherByEmployeeID(empl);
        return new ResponseEntity<>(teacherResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('TEACHER_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<?> updateTeacher(@RequestParam String id,@RequestBody @Valid CreateTeacher createTeacher){
        teacherService.updateTeacher(id, createTeacher);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('TEACHER_DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id){
        teacherService.deleteTeacher(id);
    return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }

}
