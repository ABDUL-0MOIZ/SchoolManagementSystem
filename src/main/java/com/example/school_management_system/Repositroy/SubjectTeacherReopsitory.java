package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.SubjectTeacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherReopsitory extends MongoRepository<SubjectTeacher,String> {
}
