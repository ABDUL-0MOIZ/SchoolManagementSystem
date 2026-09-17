package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subject")
@Validated
@Tag(name = "Subject",description = "CRUD Operations of Subjects apis")
public class SubjectController {
public final SubjectService subjectService;
SubjectController(SubjectService subjectService){
    this.subjectService=subjectService;
}
@PreAuthorize("hasAuthority('ROLE_Admin')")
@PostMapping("/create")
public ResponseEntity<String> create(@RequestBody @Valid SubjectRequest request){
    subjectService.create(request);
    return new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
}
    @PreAuthorize("hasAuthority('ROLE_Admin')")
@PutMapping("/update")
public ResponseEntity<String> update(@RequestParam @NotBlank String id, @RequestBody @Valid SubjectRequest request){
    subjectService.update(id,request);
    return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
}
@GetMapping("/find/{id}")
public ResponseEntity<?> getById(@PathVariable @NotBlank String id)
{

    SubjectResponse response=subjectService.findById(id);
return new ResponseEntity<>(response,HttpStatus.OK);
}
@GetMapping("/getAll")
public ResponseEntity<?> getAll(){
    List<SubjectResponse> responseList=subjectService.getAll();
    return new ResponseEntity<>(responseList,HttpStatus.OK);
}
    @PreAuthorize("hasAuthority('ROLE_Admin')")
@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
    subjectService.delete(id);
    return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
}
}
