package com.programming.applicationservice.service;

import com.programming.applicationservice.dto.ApplicationResponse;
import com.programming.applicationservice.models.Application;
import com.programming.applicationservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private  final ApplicationRepository applicationRepository;

    public List<ApplicationResponse> getApplicationsOfEmployer(long empId) {
        return applicationRepository.findByEmpId(empId).stream().map(a ->
                ApplicationResponse
                        .builder()
                        .jobId(a.getJobId())
                        .appId(a.getAppId())
                        .empId(a.getEmpId())
                        .userId(a.getUserId())
                        .build()).toList();
    }

    public  ApplicationResponse getApplicationOfApplicantForJobId(long jobId,long applicantId){
        Application a = applicationRepository.findByEmpIdAndJobId(jobId,applicantId);
        return  ApplicationResponse
                .builder()
                .jobId(a.getJobId())
                .appId(a.getAppId())
                .empId(a.getEmpId())
                .userId(a.getUserId())
                .build();
    }

    public ApplicationResponse createApplication(long empId, long userId, long jobId) {
        System.out.println("hi here");
        Application a = new Application();
        a.setUserId(userId);
        a.setEmpId(empId);
        a.setJobId(jobId);
        applicationRepository.save(a);
        return  ApplicationResponse
                .builder()
                .jobId(a.getJobId())
                .appId(a.getAppId())
                .empId(a.getEmpId())
                .userId(a.getUserId())
                .build();
    }

    public List<ApplicationResponse> getMyApplications(long userId) {
       return applicationRepository.findByUserId(userId).stream().map(a ->
               ApplicationResponse
                       .builder()
                       .jobId(a.getJobId())
                       .appId(a.getAppId())
                       .empId(a.getEmpId())
                       .userId(a.getUserId())
                       .build()).toList();

    }
}
