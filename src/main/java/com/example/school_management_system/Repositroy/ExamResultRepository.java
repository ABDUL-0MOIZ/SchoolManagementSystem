package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.ExamResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends MongoRepository<ExamResult,String> {
    List<ExamResult> findByEnrolmentIdIn(List<String> enrolmentIds);}
