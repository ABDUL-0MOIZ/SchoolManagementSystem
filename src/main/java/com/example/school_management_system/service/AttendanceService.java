package com.example.school_management_system.service;

import com.example.school_management_system.Model.Attendance;
import com.example.school_management_system.Model.EnrolGrade;
import com.example.school_management_system.Repositroy.AttendanceRepository;
import com.example.school_management_system.Repositroy.EnrolGradeRepository;
import com.example.school_management_system.dto.*;
import com.example.school_management_system.dto.Students.StudentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EnrolGradeRepository enrolGradeRepository;
   private final SectionService sectionService;
   private final StudentService studentService;
   private final  AcademicYearService academicYearService;
    public AttendanceService(AttendanceRepository attendanceRepository, EnrolGradeRepository enrolGradeRepository, SectionService sectionService, StudentService studentService, AcademicYearService academicYearService) {
        this.attendanceRepository = attendanceRepository;
        this.enrolGradeRepository = enrolGradeRepository;
        this.sectionService = sectionService;
        this.studentService = studentService;
        this.academicYearService = academicYearService;
    }

    public void create(AttendanceRequest request){
        Attendance attendance=new Attendance();
        attendance.setEnrolGradeID(request.getEnroledGradeId());
        attendance.setStatus(request.getStatus());
        attendance.setDate(request.getDate());
        attendanceRepository.save(attendance);

    }
    public void update(String id,AttendanceRequest request){
      Attendance attendance=  attendanceRepository.findById(id).orElseThrow(()->new RuntimeException("Attendance Not Found"));
        attendance.setEnrolGradeID(request.getEnroledGradeId());
        attendance.setStatus(request.getStatus());
        attendance.setDate(request.getDate());
        attendanceRepository.save(attendance);
    }
    public List<AttendanceResponse> getBySectionIdAndAcademicYear(String sectionId, String academicYear, LocalDate date){
                       List<EnrolGrade> enrolGradeList=  enrolGradeRepository.findBySectionIdAndAcademicYearId(sectionId,academicYear).orElseThrow(()->new RuntimeException("No Such Data Found"));

       List<EnrolGradeResponse> enrolGradeResponses= enrolGradeList.stream()
                .map(enrolGrade -> {

                    SectionResponse sectionResponse =
                            sectionService.getByID(
                                    enrolGrade.getSectionId());

                    StudentResponse studentResponse =
                            studentService.findStudentById(
                                    enrolGrade.getStudentId());

                    AcademicYearResponse academicYearResponse =
                            academicYearService.getByID(
                                    enrolGrade.getAcademicYearId());

                    EnrolGradeResponse response =
                            new EnrolGradeResponse();

                    response.setId(enrolGrade.getId());
                    response.setSectionResponse(sectionResponse);
                    response.setStudentResponse(studentResponse);
                    response.setAcademicYearResponse(
                            academicYearResponse);
                    response.setStatus(enrolGrade.getStatus());

                    return response;
                })
                .toList();
        List<Attendance> attendanceList = attendanceRepository.findByEnrolGradeIDInAndDate(enrolGradeList.stream().map(EnrolGrade::getId).toList(), date);
        return attendanceList.stream().map(
                attendance ->
                {
                    EnrolGradeResponse enrolGrade = enrolGradeResponses.stream()
                            .filter(e -> e.getId().equals(attendance.getEnrolGradeID()))
                            .findFirst().orElseThrow(()->{
                              return new RuntimeException("Enroled Grade Not Found");
                            });
                    AttendanceResponse response = new AttendanceResponse();

                    response.setEnrolGradeResponse(enrolGrade);
                    response.setStatus(attendance.getStatus());
                    response.setDate(attendance.getDate());

                    return response;
                }).toList();
    }
    public List<AttendanceResponse> getStudentAttendance(String enrolGradeId) {

        List<Attendance> attendanceList =
                attendanceRepository.findByEnrolGradeID(enrolGradeId);

        return attendanceList.stream()
                .map(attendance -> {
                    AttendanceResponse response = new AttendanceResponse();

                    response.setStatus(attendance.getStatus());
                    response.setDate(attendance.getDate());

                    return response;
                })
                .toList();
    }
    public void createSectionAttendance(SectionAttandanceRequest request) {

        List<Attendance> attendanceList = new ArrayList<>();

        for (StudentAttandanceRequest student : request.getStudents()) {

            Attendance attendance = new Attendance();
attendance.setStatus(student.getStatus());
attendance.setEnrolGradeID(student.getEnrolGradeId());
attendance.setDate(request.getDate());
            attendanceList.add(attendance);
        }

        attendanceRepository.saveAll(attendanceList);
    }
}
