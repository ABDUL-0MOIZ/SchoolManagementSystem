package com.example.school_management_system.Mapper;

import com.example.school_management_system.dto.CreateUser;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.dto.UserResponse;

import java.util.ArrayList;
import java.util.List;


public class UserMapper {
    public static  User createUser(CreateUser user) {
        User u = new User();
        u.setUserName(user.getUserName());
        u.setRole(user.getRole());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        return u;
    }
    public static  UserResponse mapUserResponse(User user) {
        UserResponse u = new UserResponse();
        u.setUserName(user.getUserName());
        u.setRole(user.getRole());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        return u;
    }
    public static List<UserResponse> listUserResponse(List<User> users) {
        List<UserResponse> listres = new ArrayList<>();
        users.stream().forEach(u -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setEmail(u.getEmail());
            userResponse.setPassword(u.getPassword());
            userResponse.setRole(u.getRole());
            userResponse.setUserName(u.getUserName());
            listres.add(userResponse);
        });
        return listres;
    }

}
