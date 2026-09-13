package com.example.school_management_system.service;

import com.example.school_management_system.Model.Permission;
import com.example.school_management_system.Model.Role;
import com.example.school_management_system.Model.RolePermission;
import com.example.school_management_system.Model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    UserService userService;
    public CustomUserDetailService(UserService userService){
        this.userService=userService;
            }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user= userService.findUserByEmail(email);
      if(user==null){
      throw new UsernameNotFoundException("User Not Found With this"+email);
      }

      List<String> permissions=new ArrayList<>();
      for(Role role:user.getRole()){
          permissions.add("ROLE_"+role.name());
          RolePermission.getPermssion(role).forEach(
                  permission->{
                      permissions.add(permission.name());
                  }
          );
      }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(
                        permissions.toArray(new String[0])
                )
                .build();

    }
}
