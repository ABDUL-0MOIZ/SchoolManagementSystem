package com.example.school_management_system.controller;

import com.example.school_management_system.dto.EnrolGradeRequest;
import com.example.school_management_system.dto.EnrolGradeResponse;
import com.example.school_management_system.service.EnrolGradeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
@Tag(name = "Enrolled grades",description = "Enrolled Grade of Students")
@RequestMapping("/api/v1/enrolGrade")
public class EnrolGradeController {
    private final EnrolGradeService enrolGradeService;
    public EnrolGradeController(EnrolGradeService enrolGradeService){
        this.enrolGradeService=enrolGradeService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid EnrolGradeRequest request){
        enrolGradeService.create(request);
        return new ResponseEntity<>("Succesfully created", HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @NotBlank String id,@RequestBody @Valid EnrolGradeRequest request){
        enrolGradeService.update(id, request);
        return new ResponseEntity<>("Succesfully created", HttpStatus.CREATED);

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
     EnrolGradeResponse response= enrolGradeService.findById(id);
    return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        enrolGradeService.delete(id);
        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
    }
}
