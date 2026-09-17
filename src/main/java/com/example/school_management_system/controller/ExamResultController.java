package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ExamResultRequest;
import com.example.school_management_system.dto.ExamResultResponse;
import com.example.school_management_system.dto.SectionExamResultRequest;
import com.example.school_management_system.service.ExamResultService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/ExamResult")
@Validated
public class ExamResultController {
    private final ExamResultService examResultService;
    public ExamResultController(ExamResultService examResultService){
        this.examResultService=examResultService;
    }
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ExamResultRequest request){
        examResultService.create(request);
    return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @NotBlank String id,@RequestBody @Valid ExamResultRequest request){
        examResultService.update(id,request);
        return new ResponseEntity<>("Succesfully updated",HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('STUDENT_READ')")

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        ExamResultResponse response=examResultService.getbyId(id);

    return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('STUDENT_READ')")
    @GetMapping("findBySection")
    public ResponseEntity<?> findBySectionIdAndYearId(@RequestParam @NotBlank String sectionId,@RequestParam @NotBlank String year){
        List<ExamResultResponse> responseList=examResultService.getAllBySectionAndYear(sectionId,year);
    return new ResponseEntity<>(responseList,HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ROLE_Teacher')")
    @PostMapping("createBySection")
    public ResponseEntity<?> createBySection(@RequestBody @Valid SectionExamResultRequest request)
    {
        examResultService.createSectionResult(request);
        return new ResponseEntity<>("Result Successfully Created",HttpStatus.CREATED);
    }
}
