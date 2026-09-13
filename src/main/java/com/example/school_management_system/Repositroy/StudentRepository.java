package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.Student;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<@NotNull Student, @NotNull String> {
    Student findByRollNo(String rollNo);
    void deleteByRollNo(String rollNo);
    @Query("{rollNo: {$in:?0}")
    List<Student> findByRollNoIn(List<String> rollNo);
    @Query("{gender:?0}")
    List<Student> findByGender(String Gender);
    //    public List<Student> findByName(String name);
//    public boolean existsByRolNo(String rolNo);
}
