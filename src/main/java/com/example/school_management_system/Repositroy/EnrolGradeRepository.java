package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.EnrolGrade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolGradeRepository extends MongoRepository<EnrolGrade,String> {
  Optional<List<EnrolGrade>> findBySectionIdAndAcademicYearId(String sectionId, String academicYear);
}
