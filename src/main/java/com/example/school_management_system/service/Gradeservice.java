package com.example.school_management_system.service;

import com.example.school_management_system.Model.Grade;
import com.example.school_management_system.Repositroy.GradeRepository;
import com.example.school_management_system.dto.GradeRequest;
import com.example.school_management_system.dto.GradeResponse;
import com.example.school_management_system.exception.GradeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Gradeservice {
private final GradeRepository gradeRepo;
public Gradeservice(GradeRepository gradeRepo){
    this.gradeRepo=gradeRepo;
}
public void createGrade(GradeRequest request){
    Grade grade=new Grade();
    grade.setClassName(request.getGradeName());
gradeRepo.save(grade);
}
public void updateGrade(String id,GradeRequest request){
Grade grade=    gradeRepo.findById(id).orElseThrow(()-> new GradeNotFoundException("Grade Not Found"));
grade.setClassName(request.getGradeName());
gradeRepo.save(grade);
}
public GradeResponse findById(String id){
    Grade grade=    gradeRepo.findById(id).orElseThrow(()-> new GradeNotFoundException("Grade Not Found"));
GradeResponse gradeResponse=new GradeResponse();
gradeResponse.setGradeName(grade.getClassName());
gradeResponse.setId(grade.getId());
return gradeResponse;
}
public List<GradeResponse> getAll(){
    return gradeRepo.findAll().stream().map(
            g-> {
                GradeResponse grade = new GradeResponse();
            grade.setId(g.getId());
            grade.setGradeName(g.getClassName());
           return grade;
            }
    ).toList();
}
public void delete(String id){
    gradeRepo.deleteById(id);
}
}
