package com.example.school_management_system.exception;

import org.jetbrains.annotations.NotNull;

public class SubjectNotFound extends RuntimeException {
    public SubjectNotFound(String msg) {
    super(msg);
    }
}
