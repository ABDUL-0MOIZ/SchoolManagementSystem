package com.example.school_management_system.exception;

public class UserNotFoundException extends RuntimeException{
public UserNotFoundException(String msg){
    super(msg);
}
}
