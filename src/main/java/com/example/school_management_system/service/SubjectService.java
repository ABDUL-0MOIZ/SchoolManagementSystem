package com.example.school_management_system.service;

import com.example.school_management_system.Model.Subject;
import com.example.school_management_system.Repositroy.SubjectRepositoty;
import com.example.school_management_system.dto.SubjectRequest;
import com.example.school_management_system.dto.SubjectResponse;
import com.example.school_management_system.exception.SubjectNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
private final SubjectRepositoty subjectRepositoty;
private final Gradeservice gradeservice;

    public SubjectService(Gradeservice gradeservice,SubjectRepositoty subjectRepositoty) {
        this.subjectRepositoty=subjectRepositoty;
        this.gradeservice=gradeservice;
    }
    public void create(SubjectRequest request){
        Subject subject=new Subject();
        subject.setGradeId(request.getGradeId());
        subject.setName(request.getName());
        subject.setCode(request.getCode());
        subjectRepositoty.save(subject);
    }
    public void update(String id,SubjectRequest request){
        Subject subject=subjectRepositoty.findById(id).orElseThrow(()->new SubjectNotFound("Subject Not Found"));
        subject.setGradeId(request.getGradeId());
        subject.setName(request.getName());
        subject.setCode(request.getCode());
        subjectRepositoty.save(subject);
    }
    public SubjectResponse findById(String id){
        Subject subject=subjectRepositoty.findById(id).orElseThrow(()->new SubjectNotFound("Subject Not Found"));
SubjectResponse response=new SubjectResponse();
response.setId(subject.getId());
response.setCode(subject.getCode());
response.setName(subject.getName());
response.setGradeResponse(gradeservice.findById(subject.getGradeId()));
return response;

    }
    public List<SubjectResponse> getAll(){
        return  subjectRepositoty.findAll().stream().map(subject -> {
            SubjectResponse response = new SubjectResponse();
          response.setId(subject.getId());
            response.setCode(subject.getCode());
            response.setName(subject.getName());
            response.setGradeResponse(gradeservice.findById(subject.getGradeId()));
        return response;
        } ).toList();
    }
    public void delete(String id){
        Subject subject=subjectRepositoty.findById(id).orElseThrow(()->new SubjectNotFound("Subject Not Found"));
        subjectRepositoty.delete(subject);
    }
}
