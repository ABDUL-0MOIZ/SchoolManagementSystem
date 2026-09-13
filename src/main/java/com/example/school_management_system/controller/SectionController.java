package com.example.school_management_system.controller;

import com.example.school_management_system.dto.SectionRequest;
import com.example.school_management_system.dto.SectionResponse;
import com.example.school_management_system.service.SectionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
@Validated
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    // Create Section
    @PostMapping("/create")
    public ResponseEntity<String> createSection(
            @Valid @RequestBody SectionRequest request) {

        sectionService.createSection(request);

        return ResponseEntity.ok("Section Created Successfully");
    }

    // Update Section
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable @NotBlank String id,
            @Valid @RequestBody SectionRequest request) {

        sectionService.update(id, request);

        return ResponseEntity.ok("Section Updated Successfully");
    }

    // Get Section By ID
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponse> getByID(
            @PathVariable @NotBlank String id) {

        SectionResponse response = sectionService.getByID(id);

        return ResponseEntity.ok(response);
    }

    // Get All Sections
    @GetMapping("/all")
    public ResponseEntity<List<SectionResponse>> getAll() {

        List<SectionResponse> response = sectionService.getAll();

        return ResponseEntity.ok(response);
    }

    // Delete Section
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable String id) {

        sectionService.delete(id);

        return ResponseEntity.ok("Section Deleted Successfully");
    }
}
