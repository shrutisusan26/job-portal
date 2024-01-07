package com.programming.applicantservice.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantRequest {
    private String applicantName;
    private String resumeUrl;
    private String coverLetter;
    private String address;
    private String skills;
}
