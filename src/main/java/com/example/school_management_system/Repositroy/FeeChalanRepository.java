package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.FeeChallan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeChalanRepository extends MongoRepository<FeeChallan,String> {
}
