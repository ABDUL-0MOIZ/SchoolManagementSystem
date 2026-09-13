package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.Teacher;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends MongoRepository<@NotNull Teacher,@NotNull String> {
   Optional<Teacher> findByEmployeeId(String employeeId);
}
