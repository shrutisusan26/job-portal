package com.programming.applicantservice.controller;


import com.programming.applicantservice.dto.ApplicantRequest;
import com.programming.applicantservice.dto.ApplicantResponse;
import com.programming.applicantservice.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applicant")
@RequiredArgsConstructor
public class ApplicantController {
    private  final ApplicantService applicantService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicantResponse> getAllApplicants(){
        return applicantService.getApplicants();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicantResponse getApplicantsById(@PathVariable String id ) throws Exception {
        long parseID = Long.parseLong(id);
        return  applicantService.getApplicantById(parseID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployer(@RequestBody ApplicantRequest applicantResponse) {
        applicantService.registerApplicant(applicantResponse);
    }
}
