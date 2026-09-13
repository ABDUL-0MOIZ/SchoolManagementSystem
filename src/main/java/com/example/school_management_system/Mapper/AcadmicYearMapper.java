package com.example.school_management_system.Mapper;

import com.example.school_management_system.Model.ACADEMIC_YEAR;
import com.example.school_management_system.dto.AcademicYearResponse;

public class AcadmicYearMapper {
    public static AcademicYearResponse getrespnse(ACADEMIC_YEAR academicYear){
AcademicYearResponse response=new AcademicYearResponse();
response.setEnd_date(academicYear.getEnd_date());
response.setLabel(academicYear.getLabel());
response.setStart_date(academicYear.getStart_date());
response.setId(academicYear.getId());
response.setStatu(academicYear.getStatu());
return response;
    }
}
