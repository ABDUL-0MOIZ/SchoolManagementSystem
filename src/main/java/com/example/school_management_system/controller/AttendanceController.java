package com.example.school_management_system.controller;

import com.example.school_management_system.dto.AttendanceRequest;
import com.example.school_management_system.dto.AttendanceResponse;
import com.example.school_management_system.dto.SectionAttandanceRequest;
import com.example.school_management_system.service.AttendanceService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/Attendance")
public class AttendanceController {
private final AttendanceService attendanceService;
public AttendanceController(AttendanceService attendanceService){
    this.attendanceService=attendanceService;
}
@PreAuthorize("hasAuthority('ROLE_Teacher')")
@PostMapping("/create")
    public ResponseEntity<?> create(AttendanceRequest request){
    attendanceService.create(request);
    return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
}

    @PreAuthorize("hasAuthority('ROLE_Teacher')")
@PostMapping("/bySection")
    public ResponseEntity<?> createbySection(@RequestBody SectionAttandanceRequest request){
    attendanceService.createSectionAttendance(request);
return new ResponseEntity<>("Successfully Created",HttpStatus.CREATED);
}
@PreAuthorize("hasAuthority('ROLE_Teacher')")
@PutMapping("/update")
    public ResponseEntity<?> update(@PathVariable String id,@RequestBody AttendanceRequest request){
    attendanceService.update(id,request);
    return new ResponseEntity<>("Updated Succesfully",HttpStatus.OK);

}

    @PreAuthorize("hasAuthority('STUDENT_READ')")
@GetMapping("/BySection&year")
    public ResponseEntity<?> findBySectionAndYear(@RequestParam String sectionId, @RequestParam String yearId, @RequestParam LocalDate date){
List<AttendanceResponse> attendanceResponses= attendanceService.getBySectionIdAndAcademicYear(sectionId,yearId,date);
return new ResponseEntity<>(attendanceResponses,HttpStatus.OK);
}


}
