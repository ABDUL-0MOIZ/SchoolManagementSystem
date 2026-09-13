package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.ParentMapper;
import com.example.school_management_system.Mapper.StudentManger;
import com.example.school_management_system.Mapper.UserMapper;
import com.example.school_management_system.Model.Parent;
import com.example.school_management_system.Model.Student;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.StudentRepository;
import com.example.school_management_system.dto.Students.*;
import com.example.school_management_system.exception.ParentNotFoundException;
import com.example.school_management_system.exception.StudentExceptions;
import com.example.school_management_system.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final UserService userService;
    private final ParentService parentService;
    public StudentService(StudentRepository studentRepo,ParentService parentService,UserService userService){

        this.studentRepo=studentRepo;
        this.parentService=parentService;
        this.userService=userService;

    }
    public void createStudent(CreateStudent createStudent){
        Student s=StudentManger.MapStudent(createStudent);
        if(s.getParentId().isEmpty()){
            throw new RuntimeException("Parent id is Required");
        }
        parentService.addStudentInParent(s.getRollNo());

        studentRepo.save(s);

    }
    public Page<@NotNull StudentResponse> getAllStudent(int n, int s){
     Page<@NotNull Student> students=    studentRepo.findAll(PageRequest.of(n,s,Sort.by("rollNo")));
     if(students.isEmpty()){
         throw new StudentExceptions("Student not found");
        }
return    students.map(student->{

            User u = userService.findUserByID(student.getUserId());
        Optional<Parent>  p=parentService.findParent(student.getParentId());
    if (p.isEmpty()) {
        return StudentManger.mapStudentResponse(
                student,
                null,
                UserMapper.mapUserResponse(u)
        );
    }
   User pu=  userService.findUserByID(p.get().getUserId());
   return StudentManger.mapStudentResponse(student,ParentMapper.mapParentStudentResponse(p.get(),UserMapper.mapUserResponse(pu)),UserMapper.mapUserResponse(u));
    });



    }

    public StudentResponse findStudentByRollno(String Rollno)throws UserNotFoundException {
        StudentResponse studentResponse=new StudentResponse();
        Student student = studentRepo.findByRollNo(Rollno);
        if(student==null){
            throw new StudentExceptions("Student Not Found");
        }
        User u = userService.findUserByID(student.getUserId());
       Optional<Parent> p=parentService.findParent(student.getParentId());
        if(p.isEmpty()){
            throw new ParentNotFoundException("Parent Not Found");
                }
        Parent parent=p.get();
        //pu mean Parent User
        User pu= userService.findUserByID(parent.getUserId());

        studentResponse =StudentManger.mapStudentResponse(student, ParentMapper.mapParentStudentResponse(parent,UserMapper.mapUserResponse(pu)), UserMapper.mapUserResponse(u));

        return  studentResponse;
    }
    public void updateStudent(String id,CreateStudent createStudent){
        Student s=StudentManger.MapStudent(createStudent);
      s.setId(id);
      s= studentRepo.save(s);
       if(s==null){
           throw  new StudentExceptions("Student Not Found");
       }

    }
    public void deleteStudent(String id){
       Optional<Student> s =studentRepo.findById(id);
        if (s.isEmpty()) {
            throw new StudentExceptions("Student Not Found");
        }
       userService.deleteUserByID(s.get().getUserId());
       studentRepo.deleteById(id);


    }

    public StudentResponse findStudentById(String id)throws UserNotFoundException {
        StudentResponse studentResponse;
        Student student = studentRepo.findById(id).orElseThrow(()->new StudentExceptions("Student Not Found"));

        User u = userService.findUserByID(student.getUserId());
        Optional<Parent> p=parentService.findParent(student.getParentId());
        if(p.isEmpty()){
            throw new ParentNotFoundException("Parent Not Found");
        }
        Parent parent=p.get();
        //pu mean Parent User
        User pu= userService.findUserByID(parent.getUserId());

        studentResponse =StudentManger.mapStudentResponse(student, ParentMapper.mapParentStudentResponse(parent,UserMapper.mapUserResponse(pu)), UserMapper.mapUserResponse(u));

        return  studentResponse;
    }

    }

