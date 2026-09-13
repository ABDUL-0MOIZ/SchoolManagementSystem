package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.ExamSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamScheduleRepository extends MongoRepository<ExamSchedule,String> {
}
