package com.example.school_management_system.service;

import com.example.school_management_system.Model.Exam;
import com.example.school_management_system.Repositroy.ExamRepository;
import com.example.school_management_system.dto.ExamRequest;
import com.example.school_management_system.dto.ExamResponse;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
private  final ExamRepository examRepository;
private final AcademicYearService academicYearService;
public ExamService(ExamRepository examRepository,AcademicYearService academicYearService){
    this.examRepository=examRepository;
    this.academicYearService=academicYearService;
}
public void create(ExamRequest request){
    Exam exam=new Exam();
    exam.setExamType(request.getType());
    exam.setName(request.getName());
    exam.setAcademicYearId(request.getAcademicYear());
    exam.setStartDate(request.getStartDate());
    exam.setEndDate(request.getEndDate());
examRepository.save(exam);
}
    public void update(String id,ExamRequest request){
        Exam exam=examRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Does Not Exist"));
        exam.setExamType(request.getType());
        exam.setName(request.getName());
        exam.setAcademicYearId(request.getAcademicYear());
        exam.setStartDate(request.getStartDate());
        exam.setEndDate(request.getEndDate());
        examRepository.save(exam);
}
public Exam getByid(String id){
    return examRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Does Not Exist"));

}
public ExamResponse getByID(String id){
    Exam exam= examRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Does Not Exist"));
ExamResponse response=new ExamResponse();
response.setAcademicYearResponse(academicYearService.getByID(exam.getAcademicYearId()));
response.setName(exam.getName());
response.setType(exam.getExamType());
response.setStartDate(exam.getStartDate());
response.setEndDate(exam.getEndDate());
return  response;
}
public void deleteExam(String id){
    Exam exam= examRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Does Not Exist"));
examRepository.delete(exam);
}
}
