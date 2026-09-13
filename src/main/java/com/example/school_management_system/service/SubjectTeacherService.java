package com.example.school_management_system.service;

import com.example.school_management_system.Model.Subject;
import com.example.school_management_system.Model.SubjectTeacher;
import com.example.school_management_system.Repositroy.SubjectTeacherReopsitory;
import com.example.school_management_system.dto.*;
import com.example.school_management_system.dto.Teacher.TeacherResponse;
import com.example.school_management_system.exception.SubjectTeacherNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTeacherService {
    private final SubjectTeacherReopsitory subjectTeacherRepo;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final SectionService sectionService;
    private final AcademicYearService academicYearService;
    public SubjectTeacherService(SubjectTeacherReopsitory subjectTeacherRepo, AcademicYearService academicYearService,TeacherService teacherService, SubjectService subjectService, SectionService sectionService) {
        this.subjectTeacherRepo = subjectTeacherRepo;
        this.academicYearService=academicYearService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.sectionService = sectionService;
    }

    public void create(SubjectTeacherRequest request){
        SubjectTeacher subjectTeacher=new SubjectTeacher();
        subjectTeacher.setSubjectId(request.getSubjectId());
        subjectTeacher.setTeacherId(request.getTeacherId());
        subjectTeacher.setSectionId(request.getSectionId());
        subjectTeacher.setAcademicYearId(request.getAcademicYearId());
        subjectTeacherRepo.save(subjectTeacher);
    }
    public void update(String id,SubjectTeacherRequest request){
        SubjectTeacher subjectTeacher=subjectTeacherRepo.findById(id).orElseThrow(()->new SubjectTeacherNotFound("Subject Teacher Not found"));
        subjectTeacher.setSubjectId(request.getSubjectId());
        subjectTeacher.setTeacherId(request.getTeacherId());
        subjectTeacher.setSectionId(request.getSectionId());
        subjectTeacher.setAcademicYearId(request.getAcademicYearId());
        subjectTeacherRepo.save(subjectTeacher);
    }
    public SubjectTeacherResponse findById(String id) {
        SubjectTeacher subjectTeacher = subjectTeacherRepo.findById(id).orElseThrow(() -> new SubjectTeacherNotFound("Subject Teacher Not found"));
               TeacherResponse teacherResponse=teacherService.findById(subjectTeacher.getTeacherId());
        SubjectResponse subjectResponse=subjectService.findById(subjectTeacher.getSubjectId());
        SectionResponse sectionResponse=sectionService.getByID(subjectTeacher.getSectionId());
        AcademicYearResponse yearResponse=academicYearService.getByID(subjectTeacher.getAcademicYearId());
        SubjectTeacherResponse response=new SubjectTeacherResponse();
        response.setSubjectResponse(subjectResponse);
        response.setTeacherResponse(teacherResponse);
        response.setSectionResponse(sectionResponse);
        response.setAcademicYearResponse(yearResponse);

        return response;
    }

    public List<SubjectTeacherResponse> getAll(){
      return   subjectTeacherRepo.findAll().stream().map(
                subjectTeacher -> { TeacherResponse teacherResponse=teacherService.findById(subjectTeacher.getTeacherId());
                    SubjectResponse subjectResponse=subjectService.findById(subjectTeacher.getSubjectId());
                    SectionResponse sectionResponse=sectionService.getByID(subjectTeacher.getSectionId());
                    AcademicYearResponse yearResponse=academicYearService.getByID(subjectTeacher.getAcademicYearId());
                    SubjectTeacherResponse response=new SubjectTeacherResponse();
                    response.setSubjectResponse(subjectResponse);
                    response.setTeacherResponse(teacherResponse);
                    response.setSectionResponse(sectionResponse);
                    response.setAcademicYearResponse(yearResponse);
                    return response;
                }).toList();
    }
    public  void delete(String id){
        SubjectTeacher subjectTeacher = subjectTeacherRepo.findById(id).orElseThrow(() -> new SubjectTeacherNotFound("Subject Teacher Not found"));
        subjectTeacherRepo.delete(subjectTeacher);
    }
}
