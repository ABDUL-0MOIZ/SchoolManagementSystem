package com.example.school_management_system;

import com.example.school_management_system.Model.Parent;
import com.example.school_management_system.Model.Student;
import com.example.school_management_system.Model.User;
import com.example.school_management_system.Repositroy.ParentReposiory;
import com.example.school_management_system.Repositroy.StudentRepository;
import com.example.school_management_system.Repositroy.UserRepository;
import com.example.school_management_system.dto.Students.CreateStudent;
import com.example.school_management_system.dto.Students.StudentResponse;
import com.example.school_management_system.service.ParentService;
import com.example.school_management_system.service.StudentService;
import com.example.school_management_system.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;

    @Mock
    UserService userService;
    @Mock
    ParentService parentService;
    @InjectMocks
    StudentService studentService;
    @Test
    void findbyRollNo(){

        Student student = new Student();
        student.setRollNo("ST-001");
        Parent p=new Parent();
        User u=new User();
        u.setId("U1");
        u.setEmail("mz@gmail.com");
        u.setUserName("litteljohn");
        p.setId("U12");
        p.setUserId("U1");

        student.setUserId("U1");
        student.setParentId("U12");


        when(studentRepository.findByRollNo("ST-001")).thenReturn(student);
      when(userService.findUserByID("U1")).thenReturn(u);
      when(parentService.findParent("U12")).thenReturn(Optional.of(p));
        StudentResponse result =
                studentService.findStudentByRollno("ST-001");
        assertNotNull(result);
    }
    @Test
    public void addstudent()
    {
        CreateStudent request = new CreateStudent();
        // request ki required fields set karo

        studentService.createStudent(request);

        verify(studentRepository).save(any(Student.class));


    }
    @Test
    void updateStudent() {

        CreateStudent request = new CreateStudent();
        // required fields set karo
        request.setRollNo("ST-002");

        Student savedStudent = new Student();
        savedStudent.setId("1");
        savedStudent.setRollNo("ST-002");

        when(studentRepository.save(any(Student.class)))
                .thenReturn(savedStudent);

        studentService.updateStudent("1", request);

        verify(studentRepository).save(any(Student.class));
    }
    @Test
    public void deleteStudent(){
        Student student=new Student();
        student.setId("S1");
        student.setUserId("U1");

        when(studentRepository.findById("S1")).thenReturn(Optional.of(student));
        studentService.deleteStudent("S1");
        verify(studentRepository).findById("S1");
        verify(userService).deleteUserByID(student.getUserId());

        verify(studentRepository).deleteById("S1");


    }
    @Test
    void getAllStudent() {

        Student student = new Student();
        student.setId("S1");
        student.setRollNo("ST-001");
        student.setUserId("U1");
        student.setParentId("P1");

        User user = new User();
        user.setId("U1");

        Parent parent = new Parent();
        parent.setId("P1");
        parent.setUserId("PU1");

        User parentUser = new User();
        parentUser.setId("PU1");

        Page<Student> page =
                new PageImpl<>(List.of(student));

        when(studentRepository.findAll(any(PageRequest.class)))
                .thenReturn(page);

        when(userService.findUserByID("U1"))
                .thenReturn(user);

        when(parentService.findParent("P1"))
                .thenReturn(Optional.of(parent));

        when(userService.findUserByID("PU1"))
                .thenReturn(parentUser);

        Page<StudentResponse> result =
                studentService.getAllStudent(0, 10);

        assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());

        verify(studentRepository)
                .findAll(any(PageRequest.class));
    }
}
