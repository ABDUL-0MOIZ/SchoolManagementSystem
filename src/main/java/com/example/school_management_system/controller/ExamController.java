package com.example.school_management_system.controller;

import com.example.school_management_system.dto.ExamRequest;
import com.example.school_management_system.dto.ExamResponse;
import com.example.school_management_system.service.ExamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Exam",description = "All the Api of Exam ")
@RequestMapping("api/v1/Exam")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createExam(@RequestBody @Valid ExamRequest request){
        examService.create(request);
    return new ResponseEntity<>("Successfully Created ", HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @NotBlank String id,@RequestBody @Valid ExamRequest request){
        examService.update(id, request);
        return new ResponseEntity<>("Successfully updated ", HttpStatus.OK);

    }
    @GetMapping("/get{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        ExamResponse response=examService.getByID(id);
    return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("delete{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        examService.deleteExam(id);
        return new ResponseEntity<>("Successfully Deleted ", HttpStatus.OK);

    }

}
