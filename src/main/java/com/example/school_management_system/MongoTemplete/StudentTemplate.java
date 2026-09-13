package com.example.school_management_system.MongoTemplete;

import com.example.school_management_system.Model.Student;

import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import  org.springframework.data.mongodb.core.query.Query;
import java.util.List;

public class StudentTemplate {
final MongoTemplate mongoTemplate;
public StudentTemplate(MongoTemplate mongoTemplate){
    this.mongoTemplate=mongoTemplate;
}
public List<Student> findwithGender(String gender){
    Query query=new Query();
   query.addCriteria(
           Criteria.where("gender").eq(gender)
   );
   return mongoTemplate.find(query,Student.class);
}
public void groupbygender(){
    Aggregation aggregation=Aggregation.newAggregation(
            Aggregation.group("gender").count().as("Total")
    );
    mongoTemplate.aggregate(aggregation,Student.class,String.class).getMappedResults();

}
}
