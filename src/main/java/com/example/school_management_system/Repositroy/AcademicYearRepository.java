package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.ACADEMIC_YEAR;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends MongoRepository<ACADEMIC_YEAR,String> {
}
