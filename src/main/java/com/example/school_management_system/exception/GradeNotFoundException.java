package com.example.school_management_system.exception;


public class GradeNotFoundException extends  RuntimeException{
    public GradeNotFoundException(String msg) {
        super(msg);
    }
}
