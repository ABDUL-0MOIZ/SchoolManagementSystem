package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.TeacherMapper;
import com.example.school_management_system.Mapper.UserMapper;
import com.example.school_management_system.Model.Teacher;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.TeacherRepository;
import com.example.school_management_system.dto.Teacher.CreateTeacher;
import com.example.school_management_system.dto.Teacher.TeacherResponse;
import com.example.school_management_system.exception.TeacherNotFound;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.LocalDate;
import java.util.Optional;
@Service
public class TeacherService {
    private final TeacherRepository teacherRepo;
    private final UserService userService;
    public TeacherService(TeacherRepository teacherRepository,UserService userService){
        this.teacherRepo=teacherRepository;
        this.userService=userService;
    }
    public void CreateTeacher(CreateTeacher createTeacher){

        Teacher teacher=new Teacher();
       teacher.setCnic( createTeacher.getCnic());
       teacher.setUserid(createTeacher.getUserId());
teacher.setQulification(createTeacher.getQulification());
teacher.setEmployeeId(createTeacher.getEmployeeId());
teacher.setJoinDate(new Date());
teacherRepo.save(teacher);
    }
    public Page<TeacherResponse> getAllTeacher(int pn,int s,String sortBy){
 Page< @NotNull Teacher>     teachers=   teacherRepo.findAll(PageRequest.of(pn,s, Sort.by(sortBy)));
        if (teachers.isEmpty()) {
            throw new TeacherNotFound("Teacher List is Empty");
        }

        return teachers.map(t -> {
        User  ut= userService.findUserByID(t.getUserid());
           return TeacherMapper.getResponse(t,UserMapper.mapUserResponse(ut));

                  });
    }
    public TeacherResponse getTeacherByEmployeeID(String empId){
    Teacher teacher=    teacherRepo.findByEmployeeId(empId).orElseThrow(()-> new TeacherNotFound("Teacher Not Found with this EmployeeId"));
    User user=userService.findUserByID(teacher.getUserid());

     return TeacherMapper.getResponse(teacher,UserMapper.mapUserResponse(user));

    }
    public void updateTeacher(String id,CreateTeacher createTeacher){
        Teacher savedTeacher=teacherRepo.findById(id).orElseThrow(()-> new TeacherNotFound("Teacher Not Found"));
        Teacher updatedTeacher=TeacherMapper.getRequest(createTeacher);
updatedTeacher.setId(savedTeacher.getId());
updatedTeacher.setJoinDate(savedTeacher.getJoinDate());
teacherRepo.save(updatedTeacher);
    }
    public TeacherResponse findById(String id){
        Teacher teacher=    teacherRepo.findById(id).orElseThrow(()-> new TeacherNotFound("Teacher Not Found with this EmployeeId"));
        User user=userService.findUserByID(teacher.getUserid());

        return TeacherMapper.getResponse(teacher,UserMapper.mapUserResponse(user));

    }

    public void deleteTeacher(String id){
        teacherRepo.deleteById(id);
    }
}
