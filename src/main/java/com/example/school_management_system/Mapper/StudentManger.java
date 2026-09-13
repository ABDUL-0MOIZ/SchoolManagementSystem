package com.example.school_management_system.Mapper;

import com.example.school_management_system.Model.Student;
import com.example.school_management_system.dto.Parent.ParentStudentsResponse;
import com.example.school_management_system.dto.Students.*;
import com.example.school_management_system.dto.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentManger {
public static List<StudentResponse> listStudnetResponse(List<Student> students,List<StudentParentResponse> parentResponses,List<UserResponse> users){
    List<StudentResponse> listres=new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {


        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setRolno(students.get(i).getRollNo());
        studentResponse.setUser(users.get(i));
        studentResponse.setGender(students.get(i).getGender());
        studentResponse.setDob(students.get(i).getDob());
       studentResponse.setParentResponse(parentResponses.get(i));
       listres.add(studentResponse);
      }
    return listres;

}
public static ParentStudentsResponse mapStudnetParentResponse(Student student, UserResponse userResponse){
    ParentStudentsResponse parentResponse=new ParentStudentsResponse();
    parentResponse.setDob(student.getDob());
    parentResponse.setUser(userResponse);
    parentResponse.setGender(student.getGender());
    parentResponse.setRolno(student.getRollNo());
    return  parentResponse;
}
public static  StudentResponse mapStudentResponse(Student student, StudentParentResponse parent, UserResponse u){
    StudentResponse studentResponse=new StudentResponse();
    studentResponse.setDob(student.getDob());
    studentResponse.setRolno(student.getRollNo());
    studentResponse.setGender(student.getGender());
    studentResponse.setUser(u);

   studentResponse.setParentResponse(parent);

    return  studentResponse;

}
    public static Student MapStudent(CreateStudent createStudent){
        Student student=new Student();
        student.setDob(createStudent.getDob());
        student.setGender(createStudent.getGender());
        student.setRollNo(createStudent.getRollNo());
        student.setUserId(createStudent.getUserId());
        student.setParentId(createStudent.getParentId());
        return  student;
    }
}
