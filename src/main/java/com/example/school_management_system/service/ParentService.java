package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.ParentMapper;
import com.example.school_management_system.Mapper.StudentManger;
import com.example.school_management_system.Mapper.UserMapper;
import com.example.school_management_system.Model.Parent;
import com.example.school_management_system.Model.Student;
import com.example.school_management_system.Repositroy.ParentReposiory;
import com.example.school_management_system.Repositroy.StudentRepository;
import com.example.school_management_system.dto.Parent.CreateParentReq;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.dto.Parent.ParentResponse;
import com.example.school_management_system.dto.Parent.ParentStudentsResponse;
import com.example.school_management_system.exception.ParentNotFoundException;
import com.example.school_management_system.exception.StudentExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {
    private final ParentReposiory parentRepository;
    private final StudentRepository studentrepo;
    private final  UserService user;
    public ParentService(ParentReposiory parentRepository, UserService user, StudentRepository studentrepo){
        this.parentRepository=parentRepository;
        this.user=user;
        this.studentrepo=studentrepo;
    }
    public void createParent(CreateParentReq createParentReq){
        User u=user.findUserByEmail(createParentReq.getEmail());
    Parent parent= ParentMapper.mapParent(createParentReq,u.getId());
    parentRepository.save(parent);

    }

    public Optional<Parent> findParent(String id){
       return parentRepository.findById(id);
    }

    public Page<ParentResponse> getAllParent(int pn,int s,String sortby){
    Page<Parent> parents= parentRepository.findAll(PageRequest.of(pn,s, Sort.by(sortby)));
    return parents.map(parent -> {
           User u= user.findUserByID(parent.getUserId());

           List<Student> students= studentrepo.findAllById(parent.getStudentIds());

        List<ParentStudentsResponse> parentStudentsResponses=   students.stream().map(st->{
            User su=  user.findUserByID(st.getUserId());
            return StudentManger.mapStudnetParentResponse(st, UserMapper.mapUserResponse(su));
        }).toList();
return ParentMapper.mapParentResponse(parent, UserMapper.mapUserResponse(u),parentStudentsResponses);

    });
    }

    //getParent By Email
    public ParentResponse getParent(String email) {
       User u= user.findUserByEmail(email);

       Parent parent= parentRepository.findByUserId(u.getId());
       if(parent==null){
            throw new RuntimeException("Parent Not Found with this Email");
       }

       List<Student> students= studentrepo.findAllById(parent.getStudentIds());
 List<ParentStudentsResponse> parentStudentsResponses=   students.stream().map(s->{
       User su=  user.findUserByID(s.getUserId());
         return StudentManger.mapStudnetParentResponse(s, UserMapper.mapUserResponse(su));
     }).toList();

        return    ParentMapper.mapParentResponse(parent, UserMapper.mapUserResponse(u),parentStudentsResponses);
    }
    public String addStudentInParent(String rollNo){
        Student s=studentrepo.findByRollNo(rollNo);
         if(s==null) {
             throw new StudentExceptions("Student not Found");
         }
         Optional<Parent> p=parentRepository.findById(s.getParentId());
        if(p.isEmpty()){
            throw new ParentNotFoundException("Parent Not Found");
        }
          List<String> studentIds= p.get().getStudentIds();
          studentIds.add(s.getId());
          p.get().setStudentIds(studentIds);
          return "Student Succesfully Added";


        }
        public void UpdateParent(String id,CreateParentReq createParentReq){

            Parent parent= parentRepository.findById(id).orElseThrow(()->{
                throw new RuntimeException("Parent Not Found with this Email");
            });
            parent.setStudentIds((createParentReq.getStudentid()==null)?parent.getStudentIds():createParentReq.getStudentid());
            parent.setCnic((createParentReq.getCnic().isBlank())?parent.getCnic():createParentReq.getCnic());
            parent.setPhno(createParentReq.getPhno().isBlank()?parent.getPhno():createParentReq.getPhno());
            parent.setAddress(createParentReq.getAddress().isBlank()?parent.getAddress():createParentReq.getAddress());
        parentRepository.save(parent);
    }

    public void deleteParent(String id){
    parentRepository.deleteById(id);
    }


}
