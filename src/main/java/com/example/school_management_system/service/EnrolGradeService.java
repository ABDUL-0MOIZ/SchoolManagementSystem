package com.example.school_management_system.service;

import com.example.school_management_system.Model.EnrolGrade;
import com.example.school_management_system.Repositroy.EnrolGradeRepository;
import com.example.school_management_system.dto.*;
import com.example.school_management_system.dto.Students.StudentResponse;
import com.example.school_management_system.exception.EnrolGradeNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolGradeService {

    private final EnrolGradeRepository enrolGradeRepo;
    private final SectionService sectionService;
    private final StudentService studentService;
    private final AcademicYearService academicYearService;

    public EnrolGradeService(
            EnrolGradeRepository enrolGradeRepo,
            SectionService sectionService,
            StudentService studentService,
            AcademicYearService academicYearService) {

        this.enrolGradeRepo = enrolGradeRepo;
        this.sectionService = sectionService;
        this.studentService = studentService;
        this.academicYearService = academicYearService;
    }

    // Create
    public void create(EnrolGradeRequest request) {

        EnrolGrade enrolGrade = new EnrolGrade();

        enrolGrade.setSectionId(request.getSectionId());
        enrolGrade.setStudentId(request.getStudentId());
        enrolGrade.setAcademicYearId(request.getAcademicYearId());
        enrolGrade.setStatus(request.getStatus());

        enrolGradeRepo.save(enrolGrade);
    }

    // Update
    public void update(String id, EnrolGradeRequest request) {

        EnrolGrade enrolGrade = enrolGradeRepo.findById(id)
                .orElseThrow(() ->
                        new EnrolGradeNotFound(
                                "Enrol Grade Not Found"));

        enrolGrade.setSectionId(request.getSectionId());
        enrolGrade.setStudentId(request.getStudentId());
        enrolGrade.setAcademicYearId(request.getAcademicYearId());
        enrolGrade.setStatus(request.getStatus());

        enrolGradeRepo.save(enrolGrade);
    }

    // Find By ID
    public EnrolGradeResponse findById(String id) {

        EnrolGrade enrolGrade = enrolGradeRepo.findById(id)
                .orElseThrow(() ->
                        new EnrolGradeNotFound(
                                "Enrol Grade Not Found"));

        SectionResponse sectionResponse =
                sectionService.getByID(
                        enrolGrade.getSectionId());

        StudentResponse studentResponse =
                studentService.findStudentById(
                        enrolGrade.getStudentId());

        AcademicYearResponse academicYearResponse =
                academicYearService.getByID(
                        enrolGrade.getAcademicYearId());

        EnrolGradeResponse response =
                new EnrolGradeResponse();

        response.setId(enrolGrade.getId());
        response.setSectionResponse(sectionResponse);
        response.setStudentResponse(studentResponse);
        response.setAcademicYearResponse(academicYearResponse);
        response.setStatus(enrolGrade.getStatus());

        return response;
    }

    // Get All
    public List<EnrolGradeResponse> getAll() {

        return enrolGradeRepo.findAll()
                .stream()
                .map(enrolGrade -> {

                    SectionResponse sectionResponse =
                            sectionService.getByID(
                                    enrolGrade.getSectionId());

                    StudentResponse studentResponse =
                            studentService.findStudentById(
                                    enrolGrade.getStudentId());

                    AcademicYearResponse academicYearResponse =
                            academicYearService.getByID(
                                    enrolGrade.getAcademicYearId());

                    EnrolGradeResponse response =
                            new EnrolGradeResponse();

                    response.setId(enrolGrade.getId());
                    response.setSectionResponse(sectionResponse);
                    response.setStudentResponse(studentResponse);
                    response.setAcademicYearResponse(
                            academicYearResponse);
                    response.setStatus(enrolGrade.getStatus());

                    return response;
                })
                .toList();
    }

    // Delete
    public void delete(String id) {

        EnrolGrade enrolGrade = enrolGradeRepo.findById(id)
                .orElseThrow(() ->
                        new EnrolGradeNotFound(
                                "Enrol Grade Not Found"));

        enrolGradeRepo.delete(enrolGrade);
    }
    List<EnrolGrade> getAllBySectionIdAndYear(String sectionId,String yearId){
    return     enrolGradeRepo.findBySectionIdAndAcademicYearId(sectionId,yearId).orElseThrow(()->new RuntimeException("No Data Found"));
    }
}