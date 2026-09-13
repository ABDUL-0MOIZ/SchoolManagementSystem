package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepositoty extends MongoRepository<Subject,String> {
}
