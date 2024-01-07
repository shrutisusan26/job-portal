package com.programming.applicantservice.service;


import com.programming.applicantservice.dto.ApplicantRequest;
import com.programming.applicantservice.dto.ApplicantResponse;
import com.programming.applicantservice.models.Applicant;
import com.programming.applicantservice.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    public  List<ApplicantResponse> getApplicants() {
        return applicantRepository.findAll().stream()
                .map(applicant -> {
                    return ApplicantResponse
                            .builder().applicantName(applicant.getApplicantName())
                            .coverLetter(applicant.getCoverLetter())
                            .address(applicant.getAddress())
                            .resumeUrl(applicant.getResumeUrl())
                            .skills(applicant.getSkills())
                            .ApplicantId(applicant.getApplicantId())
                            .build() ;

                }).toList();
    }

    public  ApplicantResponse getApplicantById(long parseID) throws  Exception {
        Applicant applicant =applicantRepository.findById(parseID).orElseThrow();
        return ApplicantResponse
                .builder().applicantName(applicant.getApplicantName())
                .coverLetter(applicant.getCoverLetter())
                .address(applicant.getAddress())
                .resumeUrl(applicant.getResumeUrl())
                .skills(applicant.getSkills())
                .ApplicantId(applicant.getApplicantId())
                .build() ;

    }

    public  void registerApplicant(ApplicantRequest applicantResponse){
        Applicant applicant = new Applicant();
        applicant.setApplicantName(applicantResponse.getApplicantName());
        applicant.setAddress(applicantResponse.getAddress());
        applicant.setSkills(applicantResponse.getSkills());
        applicant.setResumeUrl(applicantResponse.getResumeUrl());
        applicant.setCoverLetter(applicantResponse.getCoverLetter());
        applicantRepository.save(applicant);
    }
}
