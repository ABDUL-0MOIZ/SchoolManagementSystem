package com.example.school_management_system.controller;

import com.example.school_management_system.dto.AcademicYeaRequest;
import com.example.school_management_system.dto.AcademicYearResponse;
import com.example.school_management_system.service.AcademicYearService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/academic-year")
@Validated
public class AcademicYearController {
    private final AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/create")
    public ResponseEntity<?> createAcademicYear(@RequestBody @Valid AcademicYeaRequest request){
        log.info("Academic Year is creating.....");
        academicYearService.createAcademicYear(request);
        log.info("Successfully Academic Year is created");
        return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_Admin')")
    @PostMapping("/update")
    public ResponseEntity<?> updateAcademicYear(@RequestParam @NotBlank String id,@RequestBody @Valid AcademicYeaRequest request){
        log.info("Academic Year is updating.....");
        academicYearService.update(id,request);
        log.info("Successfully Academic Year is updated");
        return new ResponseEntity<>("Successfully Created", HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findId(@RequestParam @NotBlank String id){
       AcademicYearResponse academicYearResponse= academicYearService.getByID(id);
    return new ResponseEntity<>(academicYearResponse,HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getALl(){
       List<AcademicYearResponse> academicYearResponse= academicYearService.getAll();
        return new ResponseEntity<>(academicYearResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_Admin')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        academicYearService.deleteYear(id);
    }
}
