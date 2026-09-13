package com.example.school_management_system.exception;

import com.example.school_management_system.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentExceptions.class)
    public ResponseEntity<?> handelStudentNotFound(StudentExceptions ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new HashMap<>(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<?> handSectionNotFoundException(SectionNotFoundException ex){
        ErrorResponse eror=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),new HashMap<>(),LocalDateTime.now());
        return new ResponseEntity<>(eror,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(GradeNotFoundException.class)
    public ResponseEntity<?> handleGradeNotFoundException(GradeNotFoundException ex){
        ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),new HashMap<>(),LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TeacherNotFound.class)
    public ResponseEntity<?> handelTeacherNotFound(TeacherNotFound ex){
        ErrorResponse er=new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),new HashMap<>(),LocalDateTime.now());
   return  new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ParentNotFoundException.class)
    public ResponseEntity<?> parentNotFoundException(ParentNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), ex.getMessage(),new HashMap<>(),LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handelUserNotFound(UserNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new HashMap<>(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NotNull ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handelConstrainException(ConstraintViolationException ex){
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),ex.getMessage(),new HashMap<>(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handelRunTimeException(RuntimeException ex){
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.CONFLICT.value(),ex.getMessage(),new HashMap<>(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handelRunTimeException(Exception ex){
        ErrorResponse errorResponse=new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(),new HashMap<>(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
