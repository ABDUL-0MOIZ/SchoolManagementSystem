package com.example.school_management_system.controller;

import com.example.school_management_system.dto.Students.CreateStudent;
import com.example.school_management_system.dto.Students.StudentResponse;
import com.example.school_management_system.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@Validated
public class StudentController {

    private  StudentService studentService;
    public StudentController(StudentService studentService){

        this.studentService=studentService;
    }
@PreAuthorize("hasAuthority('STUDENT_CREATE')")
@PostMapping("/students")
    public ResponseEntity<?>  createStudent(@RequestBody @Valid CreateStudent createStudent){
    studentService.createStudent(createStudent);
    return new ResponseEntity<>("Student Successfully Created",HttpStatus.CREATED);
}

@GetMapping("students/{rollNo}")
public  ResponseEntity<?> findStudentByRollNo(@PathVariable("rollNo") String rollNo){
    System.out.println("Roll No: " + rollNo);
      StudentResponse studentResponse=  studentService.findStudentByRollno(rollNo);
return new ResponseEntity<>(studentResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @GetMapping("/students")
    public ResponseEntity<?> getAllStudent(@RequestParam @Min(value = 0) int pn,@Min(value = 3) int s){
       Page<StudentResponse> page= studentService.getAllStudent(pn, s);
    return  new ResponseEntity<>(page,HttpStatus.OK);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> UpdateStudents(@PathVariable @NotNull(message = "id is required to update Student") String id,@RequestBody @Valid CreateStudent createStudent){
        studentService.updateStudent(id,createStudent);
    return new ResponseEntity<>("Succesasfully updated",HttpStatus.CREATED);
    }
    @DeleteMapping("/students/{id}")
    public void UpdateStudents(@PathVariable @NotNull(message = "RollNo is required to update Student") String id){
        studentService.deleteStudent(id);
    }
}
