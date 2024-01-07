package com.programming.applicationservice.controller;


import com.programming.applicationservice.dto.ApplicationResponse;
import com.programming.applicationservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    @GetMapping("/my-application/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResponse> getMyApplications( @PathVariable long userId ){
        return applicationService.getMyApplications(userId);
    }

    @GetMapping("/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResponse> getAllApplicationsForEmployer(@PathVariable long empId){
        return applicationService.getApplicationsOfEmployer(empId );
    }

    @GetMapping("/{jobId}/{applicantId}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponse getApplicationOfApplicantForJobId(@PathVariable long jobId, @PathVariable long applicantId ){
        return applicationService.getApplicationOfApplicantForJobId(jobId, applicantId);
    }

    @PostMapping("/{empId}/{jobId}/{userId}")
    public ApplicationResponse applyForJobForEmployer(@PathVariable long empId, @PathVariable  long userId, @PathVariable long jobId){

        return applicationService.createApplication(empId,userId, jobId);
    }


}
