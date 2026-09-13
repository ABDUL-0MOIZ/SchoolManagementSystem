package com.example.school_management_system.Mapper;

import com.example.school_management_system.Model.Parent;
import com.example.school_management_system.dto.*;
import com.example.school_management_system.dto.Students.StudentParentResponse;
import com.example.school_management_system.dto.Parent.CreateParentReq;
import com.example.school_management_system.dto.Parent.ParentResponse;
import com.example.school_management_system.dto.Parent.ParentStudentsResponse;

import java.util.List;

public class ParentMapper {
    public static Parent mapParent(CreateParentReq parentReq, String userid) {
        Parent parent = new Parent();
        parent.setAddress(parentReq.getAddress());
        parent.setCnic(parentReq.getCnic());
        parent.setStudentIds(parentReq.getStudentid());
        parent.setPhno(parentReq.getPhno());
        parent.setUserId(userid);
        return parent;

    }

    public static ParentResponse mapParentResponse(Parent parent, UserResponse u, List<ParentStudentsResponse> studentResponseList) {
        ParentResponse parentResponse = new ParentResponse();
        parentResponse.setStudentResponses(studentResponseList);
        parentResponse.setUserResponse(u);
        parentResponse.setAddress(parent.getAddress());
        parentResponse.setPhno(parent.getPhno());
        parentResponse.setCnic(parent.getCnic());
        return parentResponse;

    }

    public static StudentParentResponse mapParentStudentResponse(Parent parent, UserResponse userResponse) {
        StudentParentResponse parentResponse = new StudentParentResponse();
        parentResponse.setAddress(parent.getAddress());
        parentResponse.setPhno(parent.getPhno());
        parentResponse.setCnic(parent.getCnic());
        parentResponse.setUserResponse(userResponse);
        return parentResponse;

    }

}
