package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends MongoRepository<Attendance,String> {
    List<Attendance> findByEnrolGradeIDInAndDate(List<String> enrolGradeIds, LocalDate date);
    boolean existsByEnrolGradeIDAndDate(
            String enrolGradeID,
            LocalDate date
    );
    List<Attendance> findByEnrolGradeID(String enrolGradeID);
}
