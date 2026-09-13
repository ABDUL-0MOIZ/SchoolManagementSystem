package com.example.school_management_system.Repositroy;

import com.example.school_management_system.Model.Parent;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentReposiory extends MongoRepository<@NotNull Parent, @NotNull String> {
     Parent findByUserId(String userId);

}
