package com.example.school_management_system.service;

import com.example.school_management_system.Mapper.UserMapper;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.UserRepository;
import com.example.school_management_system.dto.CreateUser;
import com.example.school_management_system.dto.UserResponse;
import com.example.school_management_system.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepo=userRepository;
        this.passwordEncoder=passwordEncoder;
    }
    public List<UserResponse> getUsers(){
        return UserMapper.listUserResponse(userRepo.findAll());
    }
    public void AddUser(CreateUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User u= UserMapper.createUser(user);
        userRepo.save( u);
    }
    public User findUserByEmail(String Email){
       return userRepo.findByEmail(Email);
    }
    public User findUserByID(String id){
      Optional<User> u=userRepo.findById(id);
    if(u.isEmpty()){
        throw new UserNotFoundException("User Not Found");
    }
    return u.get();
    }
    public void deleteUserByEmail(String email){
        userRepo.deleteByEmail(email);

    }
    public void updateUser(String id,CreateUser user){
      User updateduser=UserMapper.createUser(user);
        User savedUser=  userRepo.findById(id).orElseThrow(()-> new  UserNotFoundException("User not found"));
          updateduser.setId(savedUser.getId());
          updateduser.setPassword(savedUser.getPassword());
          userRepo.save(updateduser);

    }
    public void createUser(User user){
        if(user !=null){
            userRepo.save(user);
        }
    }
    public void deleteUserByID(String id){
        userRepo.deleteById(id);
    }
}
