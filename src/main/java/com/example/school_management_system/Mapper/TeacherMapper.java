package com.example.school_management_system.Mapper;

import com.example.school_management_system.Model.Teacher;
import com.example.school_management_system.Repositroy.TeacherRepository;
import com.example.school_management_system.dto.Teacher.CreateTeacher;
import com.example.school_management_system.dto.Teacher.TeacherResponse;
import com.example.school_management_system.dto.UserResponse;

import java.util.Date;

public class TeacherMapper {

    public static TeacherResponse getResponse(Teacher t, UserResponse u){
        TeacherResponse teacherResponse=new TeacherResponse();
        teacherResponse.setName(u.getUserName());
        teacherResponse.setEmail(u.getEmail());
        teacherResponse.setQualification(t.getQulification());
        teacherResponse.setCnic(t.getCnic());
        teacherResponse.setEmployeeId(t.getEmployeeId());
    return teacherResponse;
    }
    public static Teacher getRequest(CreateTeacher createTeacher){
        Teacher teacher=new Teacher();
        teacher.setCnic( createTeacher.getCnic());
        teacher.setUserid(createTeacher.getUserId());
        teacher.setQulification(createTeacher.getQulification());
        teacher.setEmployeeId(createTeacher.getEmployeeId());
        teacher.setJoinDate(new Date());
        return teacher;
    }

}
