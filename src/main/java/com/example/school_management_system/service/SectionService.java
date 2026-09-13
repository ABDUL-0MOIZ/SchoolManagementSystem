package com.example.school_management_system.service;

import com.example.school_management_system.Model.Grade;
import com.example.school_management_system.Model.Section;
import com.example.school_management_system.Repositroy.GradeRepository;
import com.example.school_management_system.Repositroy.SectionRepository;
import com.example.school_management_system.dto.ExamResponse;
import com.example.school_management_system.dto.SectionRequest;
import com.example.school_management_system.dto.SectionResponse;
import com.example.school_management_system.exception.GradeNotFoundException;
import com.example.school_management_system.exception.SectionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
private final SectionRepository sectionRepo;
private final GradeRepository gradeRepository;
public SectionService(SectionRepository sectionRepo,GradeRepository gradeRepository){
    this.sectionRepo=sectionRepo;
    this.gradeRepository=gradeRepository;
}
public void createSection(SectionRequest request){
    Section section =new Section();
section.setName(request.getName());
section.setRomeNo(request.getRomeNo());
section.setGradeId(request.getGradeId());
sectionRepo.save(section);
}

public void update(String id,SectionRequest request){
  Section savedSection=  sectionRepo.findById(id).orElseThrow(()->new SectionNotFoundException("Section Not Found"));
savedSection.setGradeId(request.getGradeId());
savedSection.setRomeNo(request.getRomeNo());
savedSection.setName(request.getName());
sectionRepo.save(savedSection);
}
public SectionResponse getByID(String id){
    Section savedSection=  sectionRepo.findById(id).orElseThrow(()->new SectionNotFoundException("Section Not Found"));
        Grade grade=gradeRepository.findById(savedSection.getGradeId()).orElseThrow(()->new GradeNotFoundException("Grade Not Found"));
    SectionResponse response=new SectionResponse();
    response.setGradeName(grade.getClassName());
    response.setSectionName(savedSection.getName());
    response.setRoomNo(savedSection.getRomeNo());

    return response;
}
public List<SectionResponse> getAll(){
  return   sectionRepo.findAll().stream().map(
         section ->
         {
             SectionResponse response=new SectionResponse();
             Grade grade=gradeRepository.findById(section.getGradeId()).orElseThrow(()->new GradeNotFoundException("Grade Not Found"));

             response.setGradeName(grade.getClassName());
             response.setSectionName(section.getName());
             response.setRoomNo(section.getRomeNo());
             return response;
         }
    ).toList();
}

public void delete(String id){
    Section section = sectionRepo.findById(id)
            .orElseThrow(() ->
                    new SectionNotFoundException("Section Not Found"));

    sectionRepo.delete(section);

}

}
