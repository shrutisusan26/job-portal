package com.programming.jobservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponse {
    private long jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private long userId;
}
