package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ExamScheduleRequest;
import com.example.school_management_system.dto.ExamScheduleResponse;
import com.example.school_management_system.service.ExamScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.coyote.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/examSchedule")
@Validated
public class ExamScheduleController {
    private final ExamScheduleService examScheduleService;

    public ExamScheduleController(ExamScheduleService examScheduleService){
        this.examScheduleService=examScheduleService;
    }
   @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ExamScheduleRequest request){
        examScheduleService.create(request);
    return new ResponseEntity<>("Sucessfully created!", HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @NotNull String id, @RequestBody @Valid ExamScheduleRequest request){
        examScheduleService.update(id, request);
    return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getById(@PathVariable @NotBlank String id){
        ExamScheduleResponse response=examScheduleService.getScheduleById(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
