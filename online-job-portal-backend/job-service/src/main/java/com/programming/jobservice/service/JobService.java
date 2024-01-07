package com.programming.jobservice.service;


import com.programming.jobservice.dto.EmployerResponse;
import com.programming.jobservice.dto.JobRequest;
import com.programming.jobservice.dto.JobResponse;
import com.programming.jobservice.models.Job;
import com.programming.jobservice.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final WebClient.Builder webClientBuilder;
    public List<JobResponse> findOpeningsForEmployer(long userId) {
        return jobRepository.findByUserId(userId).stream().map(
               job -> {
                    return JobResponse.builder()
                    .jobTitle(job.getJobTitle())
                    .jobDescription(job.getJobDescription())
                    .jobLocation(job.getJobLocation())
                            .jobId(job.getJobId())
                            .userId(job.getUserId())
                    .build();
                }
        ).toList();
    }

    public void createJobPost(JobRequest jobRequest) {
        Job job = new Job();
        job.setJobDescription(jobRequest.getJobDescription());
        job.setJobLocation(jobRequest.getJobLocation());
        job.setJobTitle(jobRequest.getJobTitle());
        job.setUserId(Long.valueOf(jobRequest.getUserId()));
        jobRepository.save(job);
    }

    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream().map(job -> {
            return JobResponse.builder()
                    .jobId(job.getJobId())
                    .jobDescription(job.getJobDescription())
                    .jobLocation(job.getJobLocation())
                    .jobTitle(job.getJobTitle())
                    .userId(job.getUserId()).build();
        }).toList();
    }

    public JobResponse findJobsByJobId(long parseId) throws Exception {
         Job job = jobRepository.findById(parseId).orElseThrow();
        return JobResponse.builder()
                    .jobId(job.getJobId())
                    .jobDescription(job.getJobDescription())
                    .jobLocation(job.getJobLocation())
                    .jobTitle(job.getJobTitle())
                    .userId(job.getUserId()).build();

    }
}
