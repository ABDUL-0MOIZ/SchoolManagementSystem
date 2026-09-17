package com.example.school_management_system.controller;

import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.service.Gradeservice;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/grade")
@Validated
@Slf4j
public class GradeController {
    private final Gradeservice gradeservice;

    public GradeController(Gradeservice gradeservice) {
        this.gradeservice = gradeservice;
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid GradeRequest request){
      log.info("creating grade....");
        gradeservice.createGrade(request);
    return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @NotBlank String id,@RequestBody @Valid GradeRequest request)
    {
        gradeservice.updateGrade(id,request);
        return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@RequestParam String id){
    GradeResponse gradeResponse= gradeservice.findById(id);
return new ResponseEntity<>(gradeResponse,HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
       return new ResponseEntity<>(gradeservice.getAll(),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable String id){
       log.info("deleting grade..............");
        gradeservice.delete(id);
        log.info("Successfully grade deleted.....");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
