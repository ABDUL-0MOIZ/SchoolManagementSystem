package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.AcadmicYearMapper;
import com.example.school_management_system.Model.ACADEMIC_YEAR;
import com.example.school_management_system.Repositroy.AcademicYearRepository;
import com.example.school_management_system.dto.AcademicYeaRequest;
import com.example.school_management_system.dto.AcademicYearResponse;
import com.example.school_management_system.exception.AcademiYearException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicYearService {
private final AcademicYearRepository academicYearRepo;
public AcademicYearService(AcademicYearRepository academicYearRepo){
    this.academicYearRepo=academicYearRepo;
}
public void createAcademicYear(AcademicYeaRequest request){
    ACADEMIC_YEAR academicYear=new ACADEMIC_YEAR();
    academicYear.setLabel(request.getLabel());
    academicYear.setStart_date(request.getStart_date());
    academicYear.setEnd_date(request.getEnd_date());
    academicYear.setStatu(request.getStatu());
    academicYearRepo.save(academicYear);
}
public void update(String id,AcademicYeaRequest request){
    ACADEMIC_YEAR savedyear=academicYearRepo.findById(id).orElseThrow(()-> new AcademiYearException("Academic Year Not Found"));
    ACADEMIC_YEAR academicYear=new ACADEMIC_YEAR();
    academicYear.setId(savedyear.getId());

    academicYear.setLabel(request.getLabel());
    academicYear.setStart_date(request.getStart_date());
    academicYear.setEnd_date(request.getEnd_date());
    academicYear.setStatu(request.getStatu());
    academicYearRepo.save(academicYear);
}
public AcademicYearResponse getByID(String id){
    ACADEMIC_YEAR savedyear=academicYearRepo.findById(id).orElseThrow(()-> new AcademiYearException("Academic Year Not Found"));

    return AcadmicYearMapper.getrespnse(savedyear);
}
public List<AcademicYearResponse> getAll(){
   return academicYearRepo.findAll().stream().map(
           AcadmicYearMapper::getrespnse
    ).toList();
}
public void deleteYear(String id){
    academicYearRepo.deleteById(id);

}

}
