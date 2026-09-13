package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
 User findByEmail(String email);
void deleteByEmail(String email);
}
