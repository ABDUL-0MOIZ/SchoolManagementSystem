package com.example.school_management_system.exception;

import org.jetbrains.annotations.NotNull;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(String sectionNotFound) {
    }
}
