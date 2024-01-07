package com.programming.jobservice.controller;


import com.programming.jobservice.dto.JobResponse;
import com.programming.jobservice.dto.JobRequest;

import com.programming.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
        private final JobService jobService;


        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public List<JobResponse> getAllJobs(){
            return jobService.getAllJobs();
        }

        @GetMapping("/emp/{empId}")
        @ResponseStatus(HttpStatus.OK)
        public List<JobResponse> findJobsOfEmployer(@PathVariable String empId){
            long parseId = Long.parseLong(empId);
            return jobService.findOpeningsForEmployer(parseId);
        }

        @GetMapping("/{jobId}")
        @ResponseStatus(HttpStatus.OK)
        public JobResponse findJobsByJobID(@PathVariable String jobId ) throws Exception {
            long parseId = Long.parseLong(jobId);
            return jobService.findJobsByJobId(parseId);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public void createJobPost(@RequestBody JobRequest jobRequest){
             jobService.createJobPost(jobRequest);
        }


}
