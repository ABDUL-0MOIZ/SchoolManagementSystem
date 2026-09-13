package com.example.school_management_system.exception;

import org.jetbrains.annotations.NotNull;

public class SubjectTeacherNotFound extends RuntimeException{
    public SubjectTeacherNotFound(String msg) {
    super(msg);
    }
}
