package com.example.school_management_system.service;

import com.example.school_management_system.Model.ExamSchedule;
import com.example.school_management_system.Repositroy.ExamScheduleRepository;
import com.example.school_management_system.dto.ExamScheduleRequest;
import com.example.school_management_system.dto.ExamScheduleResponse;
import org.springframework.stereotype.Service;

@Service
public class ExamScheduleService {
private final ExamScheduleRepository examScheduleRepository;
private final SubjectService subjectService;
private final ExamService examService;
private final SectionService sectionService;

    public ExamScheduleService(ExamScheduleRepository examScheduleRepository, SubjectService subjectService, ExamService examService, SectionService sectionService) {
        this.examScheduleRepository = examScheduleRepository;
        this.subjectService = subjectService;
        this.examService = examService;
        this.sectionService = sectionService;
    }

    public void create(ExamScheduleRequest request){
    ExamSchedule examSchedule=new ExamSchedule();
    examSchedule.setEndTime(request.getEndTime());
    examSchedule.setExamId(request.getExamId());
    examSchedule.setSectionID(request.getSectionID());
    examSchedule.setStartTime(request.getStartTime());
    examSchedule.setSubjectId(request.getSubjectId());
    examScheduleRepository.save(examSchedule);

}
public void update(String id,ExamScheduleRequest request){
    ExamSchedule examSchedule=examScheduleRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Schedule Not Found"));
    examSchedule.setEndTime(request.getEndTime());
    examSchedule.setExamId(request.getExamId());
    examSchedule.setSectionID(request.getSectionID());
    examSchedule.setStartTime(request.getStartTime());
    examSchedule.setSubjectId(request.getSubjectId());
    examScheduleRepository.save(examSchedule);
}
public ExamScheduleResponse getScheduleById(String id){
    ExamSchedule examSchedule=examScheduleRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Schedule Not Found"));
    ExamScheduleResponse response=new ExamScheduleResponse();
    response.setSubject(subjectService.findById(examSchedule.getSubjectId()));
    response.setSection(sectionService.getByID(examSchedule.getSectionID()));
    response.setExamResponse(examService.getByID(examSchedule.getExamId()));
    response.setStartTime(examSchedule.getStartTime());
    response.setStartDate(examSchedule.getStartDate());
    return response;

}
public void delete(String id){{
    ExamSchedule examSchedule=examScheduleRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Schedule Not Found"));
examScheduleRepository.delete(examSchedule);
}
}
}
