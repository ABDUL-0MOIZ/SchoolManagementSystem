package com.example.school_management_system.service;

import com.example.school_management_system.Model.EnrolGrade;
import com.example.school_management_system.Model.ExamResult;
import com.example.school_management_system.Repositroy.ExamResultRepository;
import com.example.school_management_system.dto.ExamResultRequest;
import com.example.school_management_system.dto.ExamResultResponse;
import com.example.school_management_system.dto.SectionExamResultRequest;
import com.example.school_management_system.dto.StudentExamResultRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamResultService {
private final ExamResultRepository examResultRepository;
private final ExamScheduleService examScheduleService;
private final EnrolGradeService enrolGradeService;

    public ExamResultService(ExamResultRepository examResultRepository, ExamScheduleService examScheduleService, EnrolGradeService enrolGradeService) {
        this.examResultRepository = examResultRepository;
        this.examScheduleService = examScheduleService;
        this.enrolGradeService = enrolGradeService;
    }

    public void create(ExamResultRequest request){
        ExamResult examResult=new ExamResult();
        examResult.setExamScheduleId(request.getExamScheduleId());
        examResult.setGrade(request.getGrade());
        examResult.setRemarks(request.getRemarks());
        examResult.setEnrolmentId(request.getEnrolmentId());
        examResult.setObtainMarks(request.getObtainMarks());
examResult.setTotalMarks(request.getTotalMarks());
examResultRepository.save(examResult);


    }
    public void update(String id,ExamResultRequest request){
        ExamResult examResult=examResultRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Result Not Found"));
        examResult.setExamScheduleId(request.getExamScheduleId());
        examResult.setGrade(request.getGrade());
        examResult.setRemarks(request.getRemarks());
        examResult.setEnrolmentId(request.getEnrolmentId());
        examResult.setObtainMarks(request.getObtainMarks());
        examResult.setTotalMarks(request.getTotalMarks());
        examResultRepository.save(examResult);
    }
   public ExamResultResponse getbyId(String id){
       ExamResult examResult=examResultRepository.findById(id).orElseThrow(()->new RuntimeException("Exam Result Not Found"));
   ExamResultResponse response=new ExamResultResponse();

   response.setExamScheduleResponse(examScheduleService.getScheduleById(examResult.getExamScheduleId()));
       response.setEnrolGradeResponse(enrolGradeService.findById(examResult.getEnrolmentId()));
       response.setTotalMarks(examResult.getTotalMarks());
       response.setObtainMarks(examResult.getObtainMarks());
       response.setGrade(examResult.getGrade());
       response.setRemarks(examResult.getRemarks());
       return response;
   }
   public List<ExamResultResponse> getAllBySectionAndYear(String sectionId,String yearId){
     List<EnrolGrade> enrolGrades= enrolGradeService.getAllBySectionIdAndYear(sectionId,yearId);
    List<ExamResult> examResultList= examResultRepository.findByEnrolmentIdIn( enrolGrades.stream().map(EnrolGrade::getId).toList());
   return  examResultList.stream().map(examResult -> {
       ExamResultResponse response = new ExamResultResponse();

       response.setExamScheduleResponse(examScheduleService.getScheduleById(examResult.getExamScheduleId()));
       response.setEnrolGradeResponse(enrolGradeService.findById(examResult.getEnrolmentId()));
       response.setTotalMarks(examResult.getTotalMarks());
       response.setObtainMarks(examResult.getObtainMarks());
       response.setGrade(examResult.getGrade());
       response.setRemarks(examResult.getRemarks());
       return response;
   } ).toList();


    }
    public void createSectionResult(SectionExamResultRequest request) {

        List<EnrolGrade> enrolGrades =
                enrolGradeService.getAllBySectionIdAndYear(
                        request.getSectionId(),
                        request.getYearId()
                );

        List<ExamResult> results = new ArrayList<>();

        for (StudentExamResultRequest student : request.getStudents()) {

            ExamResult examResult = new ExamResult();

            examResult.setExamScheduleId(request.getExamScheduleId());
            examResult.setEnrolmentId(student.getEnrolmentId());
            examResult.setTotalMarks(student.getTotalMarks());
            examResult.setObtainMarks(student.getObtainMarks());
            examResult.setGrade(student.getGrade());
            examResult.setRemarks(student.getRemarks());

            results.add(examResult);
        }

        examResultRepository.saveAll(results);
    }
}
