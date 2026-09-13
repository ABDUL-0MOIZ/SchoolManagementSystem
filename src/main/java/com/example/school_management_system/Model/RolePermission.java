package com.example.school_management_system.Model;

import java.util.Set;

public class RolePermission {

public static Set<Permission> getPermssion(Role role){
    switch (role){
        case Admin -> {
            return Set.of(Permission.STUDENT_CREATE,
                    Permission.STUDENT_READ,
                    Permission.STUDENT_UPDATE,
                    Permission.STUDENT_DELETE,
                    Permission.TEACHER_CREATE,
                    Permission.TEACHER_READ,
                    Permission.TEACHER_UPDATE,
                    Permission.TEACHER_DELETE,
                    Permission.USER_READ,
                    Permission.PARENT_CREATE,
                    Permission.PARENT_DELETE,
                    Permission.PARENT_UPDATE,
                    Permission.PARENT_READ
            );
        }
        case Student -> {
            return Set.of(
                    Permission.STUDENT_READ,
                    Permission.PARENT_READ,
                    Permission.TEACHER_READ

            );
        }
        case Teacher ->
        {
            return   Set.of(
                       Permission.TEACHER_READ,
                       Permission.STUDENT_UPDATE,
                       Permission.PARENT_READ,
                       Permission.TEACHER_UPDATE

               );
        }
        case Parent ->
        {
            return Set.of(
                      Permission.TEACHER_READ,
                      Permission.STUDENT_READ,
                      Permission.PARENT_READ,
                      Permission.PARENT_UPDATE
              );
        }


    }
    return Set.of();
}

}
